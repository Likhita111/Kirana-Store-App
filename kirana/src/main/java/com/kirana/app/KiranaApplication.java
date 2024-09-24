package com.kirana.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KiranaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KiranaApplication.class, args);
		System.out.println("Hello Customers");
	}

}
