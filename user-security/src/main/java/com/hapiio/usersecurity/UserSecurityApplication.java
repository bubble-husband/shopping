package com.hapiio.usersecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserSecurityApplication.class, args);
    }

}
