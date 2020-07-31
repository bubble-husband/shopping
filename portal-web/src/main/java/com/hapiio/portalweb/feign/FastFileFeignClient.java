package com.hapiio.portalweb.feign;


import com.hapiio.pojo.model.FastDfsFile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("fastdfs-service")
public interface FastFileFeignClient {

    @GetMapping("/fastdfs/findByType")
    public List<FastDfsFile> findByType(@RequestParam("type") Integer type);
}
