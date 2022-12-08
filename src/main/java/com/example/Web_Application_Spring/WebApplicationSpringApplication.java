package com.example.Web_Application_Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class WebApplicationSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplicationSpringApplication.class, args);
	}

}
