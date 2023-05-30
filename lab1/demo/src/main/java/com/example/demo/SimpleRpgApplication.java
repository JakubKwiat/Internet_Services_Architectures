package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.Builder;
@SpringBootApplication
@Builder
public class SimpleRpgApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleRpgApplication.class, args);
	}

}
