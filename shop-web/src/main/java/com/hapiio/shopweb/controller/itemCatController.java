package com.hapiio.shopweb.controller;

import com.hapiio.pojo.model.ItemCat;
import com.hapiio.shopweb.feign.ItemCatFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itemCat")
public class itemCatController {

    @Autowired
    private ItemCatFeignClient itemCatFeignClient;

    @GetMapping("/searchItemCatByParentId")
    public List<ItemCat> searchItemCatByParentId(@RequestParam("parentId") Long parentId){
        System.out.println(parentId);
        return itemCatFeignClient.searchItemCatByParentId(parentId);
    }
}
