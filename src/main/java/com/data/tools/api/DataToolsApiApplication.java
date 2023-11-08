package com.data.tools.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class DataToolsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataToolsApiApplication.class, args);
	}

}
