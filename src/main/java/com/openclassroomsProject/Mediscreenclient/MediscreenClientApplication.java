package com.openclassroomsProject.Mediscreenclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author jonathan GOUVEIA
 * @version 1.0
 */
@SpringBootApplication
@EnableFeignClients
public class MediscreenClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenClientApplication.class, args);
	}
}