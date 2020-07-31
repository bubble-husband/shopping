package com.hapiio.managerweb.controller;

import com.hapiio.commons.common.PageResult;
import com.hapiio.commons.common.Result;
import com.hapiio.managerweb.feign.BrandFeignClient;
import com.hapiio.pojo.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/brand")
public class BrandController {

    private static final String REST_URL_PREFIX = "http://sellergoods-service";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BrandFeignClient brandFeignClient;

    @RequestMapping("/findAll")
    public List<Brand> findAll() {
        return restTemplate.getForObject(REST_URL_PREFIX+"/brand/findAll",List.class);
    }

    @GetMapping("/findPage")
    public PageResult findPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        return brandFeignClient.findPage(pageNo,pageSize);
    }

    @PostMapping("/add")
    public Result addBrand(@RequestBody Brand brand) {
        return brandFeignClient.addBrand(brand);
    }

    @PostMapping("/update")
    public Result updateBrand(@RequestBody Brand brand) {
        return brandFeignClient.updateBrand(brand);
    }

    @GetMapping("/findOne")
    public Brand findBrand(@RequestParam("id") Long id) {
        return brandFeignClient.findBrand(id);
    }

    @RequestMapping("/delete")
    public Result deleteBrand(@RequestParam("ids") Long[] ids) {
        return brandFeignClient.deleteBrand(ids);
    }

    @PostMapping("/search")
    public PageResult searchBrand(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,@RequestBody Brand brand) {
        System.out.println(brand.getName());
        return brandFeignClient.searchBrand(pageNo,pageSize,brand);
    }

    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList() {
        return brandFeignClient.selectOptionList();
    }



}
