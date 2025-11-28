package com.caragies.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${vpic.base-url}")
    private String vpicBaseUrl;

    @Value("${ollama.base-url}")
    private String ollamaBaseUrl;

    @Bean(name = "vpicWebClient")
    public WebClient vpicWebClient() {
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(config -> config.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .build();

        return WebClient.builder()
                .baseUrl(vpicBaseUrl)
                .exchangeStrategies(strategies)
                .build();
    }

    @Bean(name = "ollamaWebClient")
    public WebClient ollamaWebClient() {
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(config -> config.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .build();

        return WebClient.builder()
                .baseUrl(ollamaBaseUrl)
                .exchangeStrategies(strategies)
                .build();
    }
}
