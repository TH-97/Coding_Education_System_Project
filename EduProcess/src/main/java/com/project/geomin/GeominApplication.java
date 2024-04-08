package com.project.geomin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.geomin.user.util.IdValidator;

@SpringBootApplication
public class GeominApplication {

	IdValidator idvalidator ;
	
	public static void main(String[] args) {
		SpringApplication.run(GeominApplication.class, args);
	}

}
