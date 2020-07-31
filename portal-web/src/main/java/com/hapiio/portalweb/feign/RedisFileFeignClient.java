package com.hapiio.portalweb.feign;

import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.Content;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("redis-service")
public interface RedisFileFeignClient {

    @PostMapping("/list/save")
    public Result saveList(@RequestParam("key") String key, List<Content> contents);

    @GetMapping("/list/find")
    public List findList(@RequestParam("key") String key);


}
