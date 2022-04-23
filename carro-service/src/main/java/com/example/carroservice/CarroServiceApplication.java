package com.example.carroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class CarroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarroServiceApplication.class, args);
    }

}
