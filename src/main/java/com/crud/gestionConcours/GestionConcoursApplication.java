package com.crud.gestionconcours;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
public class GestionConcoursApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionConcoursApplication.class, args);
	}

}
