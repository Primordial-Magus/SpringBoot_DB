package com.eric.rizz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class RizzApplication {

	public static void main(String[] args) {

//		var ctx = SpringApplication.run(RizzApplication.class, args);
		var app = new SpringApplication(RizzApplication.class);
		app.setDefaultProperties(Collections.singletonMap("spring.profiles.active", ""));
		var ctx = app.run(args);

//		System.out.println(myFirstService.getCustomPropertyFromAnotherFile());

	}

}
