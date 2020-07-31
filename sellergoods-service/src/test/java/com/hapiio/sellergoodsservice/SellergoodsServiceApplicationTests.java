package com.hapiio.sellergoodsservice;

import com.hapiio.sellergoodsservice.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
class SellergoodsServiceApplicationTests {

    @Resource
    private BrandService brandService;

    @Test
    void contextLoads() {

        System.out.println(brandService.finAll());
    }

}
