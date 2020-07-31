package com.hapiio.shopweb.feign;


import com.hapiio.pojo.model.ItemCat;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient("business-service")
public interface ItemCatFeignClient {

    @GetMapping("/itemCat/searchItemCatByParentId")
    public List<ItemCat> searchItemCatByParentId(@RequestParam("parentId") Long parentId);


}
