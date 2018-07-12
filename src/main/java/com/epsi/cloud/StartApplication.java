package com.epsi.cloud;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class StartApplication {		
	@RequestMapping("/")
	String hello() {
		return "MICRO SERVICE / Groupe Commentaire : Mbaye Kebe SECK, "
				+ "Florient SCHMITT, "
				+ "Grégory KALOUGUINE, "
				+ "David DEVOS";
	}
	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);		
	}
}
