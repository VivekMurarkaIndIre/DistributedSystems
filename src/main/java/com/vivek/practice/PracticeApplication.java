package com.vivek.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication is composed of following annotations
 * enables auto-configuration (@EnableAutoConfiguration) =>Enable spring-boot auto configuration support,
 * component scanning(@ComponentScan)=> Enables component scanning of current package, auto recussively scan sub-packages,
 * allows us to leverage default scanning; no need to explicitly reference the base package name as long it is a sub package
 * for additional package other than sub package we need to use scanBasePackages
 * additonal configuration(@Configuration)=> able to register extra beans with @Bean or import other configuration classes
 */
//@SpringBootApplication(scanBasePackages = {"com.bosch","com.vivek","com.oracle"})
@SpringBootApplication
public class PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}

}
