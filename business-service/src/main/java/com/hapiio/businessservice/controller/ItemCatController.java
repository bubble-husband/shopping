package com.hapiio.businessservice.controller;

import com.hapiio.businessservice.service.ItemCatService;
import com.hapiio.pojo.model.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @GetMapping("/searchItemCatByParentId")
    public List<ItemCat> searchItemCatByParentId(@RequestParam("parentId") Long parentId){
        System.out.println(parentId);
        return itemCatService.searchItemCatByParentId(parentId);
    }
}
