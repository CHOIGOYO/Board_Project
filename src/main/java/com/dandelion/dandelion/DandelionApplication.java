package com.dandelion.dandelion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DandelionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DandelionApplication.class, args);
	}

}
