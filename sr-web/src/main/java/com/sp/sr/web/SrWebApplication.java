package com.sp.sr.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.sp.sr.model.repository")
@EntityScan(basePackages = "com.sp.sr.model.domain")
public class SrWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrWebApplication.class, args);
    }
}
