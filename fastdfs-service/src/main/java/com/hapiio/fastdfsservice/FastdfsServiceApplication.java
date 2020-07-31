package com.hapiio.fastdfsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FastdfsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastdfsServiceApplication.class, args);
    }

}
