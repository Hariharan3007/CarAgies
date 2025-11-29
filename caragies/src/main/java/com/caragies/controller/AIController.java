package com.caragies.controller;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/ai")
public class AIController {
    private final WebClient vpicClient;
    private final WebClient ollamaClient;
    public AIController(@Qualifier("vpicWebClient") WebClient vpicWebClient,
                         @Qualifier("ollamaWebClient") WebClient ollamaWebClient) {
        this.vpicClient = vpicWebClient;
        this.ollamaClient = ollamaWebClient;
    }
    @GetMapping("/models")
    public Mono<ResponseEntity<Map<String, Object>>> getModelsForMakeYear(@RequestParam String make,
                                                                          @RequestParam int year)
    {
        String path = String.format("/api/vehicles/GetModelsForMakeYear/make/%s/modelyear/%d?format=json",
                make, year);
        return vpicClient.get()
                .uri(path)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Map.class)
                .map(body -> ResponseEntity.ok((Map<String,Object>) body))
                .timeout(Duration.ofSeconds(10))
                .onErrorResume(ex -> {
                    Map<String, Object> err = Map.of(
                            "error", "vpic_unreachable",
                            "details", ex.getMessage()
                    );
                    return Mono.just(ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(err));
                });
    }
    @PostMapping("/llm/about")
    public Mono<ResponseEntity<Map<String, Object>>> aboutCar(@RequestBody Map<String, Object> body) {

        final String make = Optional.ofNullable(body.get("make")).map(Object::toString).orElse("").trim();
        final Integer year;
        try {
            year = Optional.ofNullable(body.get("year")).map(x -> Integer.parseInt(x.toString())).orElse(null);
        } catch (Exception e) {
            return Mono.just(ResponseEntity.badRequest().body(Map.of("error", "invalid_year")));
        }
        if (make.isEmpty() || year == null || year <= 0) {
            return Mono.just(ResponseEntity.badRequest().body(Map.of("error", "invalid_input")));
        }

        final String path = String.format("/api/vehicles/GetModelsForMakeYear/make/%s/modelyear/%d?format=json",
                make, year);

        return vpicClient.get()
                .uri(path)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Map.class)
                .timeout(Duration.ofSeconds(10))
                .map(vpicRespRaw -> {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> vpicResp = (vpicRespRaw instanceof Map)
                            ? (Map<String, Object>) vpicRespRaw
                            : Map.of();

                    // Trim Results to at most 10 and update Count
                    Map<String, Object> trimmed = new LinkedHashMap<>(vpicResp);
                    Object resultsObj = vpicResp.get("Results");
                    if (resultsObj instanceof List<?>) {
                        List<?> results = (List<?>) resultsObj;
                        int keep = Math.min(10, results.size());
                        List<?> sub = results.subList(0, keep);
                        trimmed.put("Results", sub);
                        trimmed.put("Count", keep);
                    }
                    return ResponseEntity.ok(trimmed);
                })
                .onErrorResume(ex -> Mono.just(
                        ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(Map.of("error", "service_unavailable"))
                ));
    }


    // unchanged safe prompt builder
    private String buildPromptFromVPICSafe(Map<String, Object> vpicResp,
                                           String make, int year,
                                           String task, Map<String, Object> requestBody) {
        StringBuilder sb = new StringBuilder();
        sb.append("Car facts (from NHTSA vPIC). Make: ").append(make)
                .append(", Year: ").append(year).append("\n");

        Object resultsObj = vpicResp.get("Results");
        if (resultsObj instanceof List<?>) {
            List<?> results = (List<?>) resultsObj;
            sb.append("Found ").append(results.size()).append(" model entries. Example rows:\n");
            for (int i = 0; i < Math.min(5, results.size()); ++i) {
                Object item = results.get(i);
                if (item instanceof Map<?, ?>) {
                    Map<?, ?> r = (Map<?, ?>) item;
                    Object modelName = r.get("Model_Name");
                    if (modelName == null) modelName = r.get("model_name");
                    Object makeName = r.get("Make_Name");
                    if (makeName == null) makeName = r.get("make_name");
                    sb.append("- model: ").append(String.valueOf(modelName))
                            .append(", make: ").append(String.valueOf(makeName)).append("\n");
                } else {
                    sb.append("- ").append(String.valueOf(item)).append("\n");
                }
            }
        } else {
            sb.append("Results not present or in unexpected format.\n");
        }

        sb.append("\nInstruction: Using ONLY the facts above, perform the task: ").append(task).append("\n");
        sb.append("Formatting: return JSON keys: summary, bullets (list), suggestion. Be concise. Do not invent specs.\n");

        if (requestBody.containsKey("buyer_profile")) {
            sb.append("Buyer profile: ").append(requestBody.get("buyer_profile")).append("\n");
        }
        if (requestBody.containsKey("symptom")) {
            sb.append("Symptom: ").append(requestBody.get("symptom")).append("\n");
        }

        sb.append("\nExample output:\n{\"summary\":\"...\",\"bullets\":[\"...\"],\"suggestion\":\"...\"}\n");
        return sb.toString();
    }
}
