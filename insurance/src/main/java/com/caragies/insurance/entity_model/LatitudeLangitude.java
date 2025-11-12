package com.caragies.insurance.entity_model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LatitudeLangitude {
    private String ip;
    private String city;
    private String region;
    private String country;
    private String loc;
    private String org;
    private String postal;
    private String timezone;
    private String readme;

}
