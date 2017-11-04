package com.sp.sr.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackages = "com.sp.sr.model.domain")
public class SrWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SrWebApplication.class, args);
    }
}
