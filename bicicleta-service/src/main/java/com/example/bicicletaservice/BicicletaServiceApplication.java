package com.example.bicicletaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BicicletaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BicicletaServiceApplication.class, args);
    }

}
