package com.sp.sr.admin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.sp.sr.model.*")
public class SrAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(SrAdminApplication.class, args);
	}
}
