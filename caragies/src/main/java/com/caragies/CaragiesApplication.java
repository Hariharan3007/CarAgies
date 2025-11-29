package com.caragies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.caragies.repositories")
@EntityScan(basePackages = "com.caragies.entitymodel")
@EnableScheduling
public class CaragiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaragiesApplication.class, args);
	}

}
