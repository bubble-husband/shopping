package com.hapiio.redisservice.controller;

import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.Content;
import com.hapiio.redisservice.service.RedisListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("redisList")
public class RedisListController {

    @Autowired
    private RedisListService redisListService;

    @PostMapping("/save")
    public Result save(@RequestParam("key") String key,@RequestBody List<Content> contents){
        return redisListService.save(key,contents);
    }

    @GetMapping("/find")
    public List find(@RequestParam("key") String key){
        return redisListService.find(key);
    }

}
