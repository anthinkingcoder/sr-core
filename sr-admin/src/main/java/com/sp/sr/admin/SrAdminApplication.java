package com.sp.sr.admin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.sp.sr.model.*")
@EnableJpaRepositories(basePackages = "com.sp.sr.model.repository")
public class SrAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(SrAdminApplication.class, args);
	}
}
