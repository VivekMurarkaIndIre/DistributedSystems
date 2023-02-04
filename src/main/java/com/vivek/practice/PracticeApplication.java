package com.vivek.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication is composed of following annotations
 * enables auto configuration(@EnableAutoConfiguration) =>Enable spring-boot auto configuration support,
 * component scanning(@ComponentScan)=> Enables component scanning of current package, auto recussively scan sub-packages,
 * additonal configuration(@Configuration)=> able to register extra beans with @Bean or import other configuration classes
 */
@SpringBootApplication
public class PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}

}
