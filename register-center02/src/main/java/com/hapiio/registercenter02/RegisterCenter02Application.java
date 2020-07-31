package com.hapiio.registercenter02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegisterCenter02Application {

    public static void main(String[] args) {
        SpringApplication.run(RegisterCenter02Application.class, args);
    }

}
