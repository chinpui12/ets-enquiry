package com.nus.iss.enquiry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.nus.iss.*")
public class EnquiryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnquiryApplication.class, args);
	}

}
