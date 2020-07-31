package com.hapiio.sellergoodsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class SellergoodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellergoodsServiceApplication.class, args);
    }

}
