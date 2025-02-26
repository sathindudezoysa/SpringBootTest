package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// @SpringBootApplication 
/*
 * This one annotation have three anotation included
 * 1. Enable Auto Configuration
 * 2. Configuration
 * 3. Component Scan //This anotation is for set the scope to check the controllers
 * -> Instead of using one anotation bellow I sperate the anotations to broder the scope
 */

@EnableAutoConfiguration
@Configuration
@ComponentScan("com.example")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
