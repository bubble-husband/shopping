package com.hapiio.registercenter01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegisterCenter01Application {

    public static void main(String[] args) {
        SpringApplication.run(RegisterCenter01Application.class, args);
    }

}
