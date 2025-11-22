package com.caragies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CaragiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaragiesApplication.class, args);
	}

}
