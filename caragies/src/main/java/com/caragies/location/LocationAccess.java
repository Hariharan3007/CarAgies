package com.caragies.location;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class LocationAccess {
    RestTemplate restTemplate;


    private String getAddress(double lat, double lon) {
        String url = "https://nominatim.openstreetmap.org/reverse?lat=" + lat + "&lon=" + lon + "&format=json";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    public String getLocation() {
        String url = "https://ipinfo.io/json"; // Open API (no key required for small usage)
        LatitudeLangitude latitudeLangitude  = restTemplate.getForObject(url,LatitudeLangitude.class);
        String tem=latitudeLangitude.getLoc();
        System.out.println(tem);
       String parts[]= tem.split(",");
     String  response= getAddress(Double.parseDouble(parts[0]),Double.parseDouble(parts[1]));

       try{
           ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);

        String displayName = root.path("display_name").asText();
        return displayName != null ? displayName : "Address not found";

    } catch (Exception e) {
        e.printStackTrace();
        return "Error fetching address";
    }
    }
}
