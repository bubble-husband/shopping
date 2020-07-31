package com.hapiio.shopweb.feign;

import com.hapiio.pojo.model.FastDfsFile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@FeignClient("fastdfs-service")
public interface FastDfsFileFeignClient {

    @PostMapping(value = "/fastdfs/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FastDfsFile upload(@RequestPart(value = "file",required = false) MultipartFile multipartFile);

    @GetMapping("/fastdfs/download")
    public byte[] download(@RequestParam("id") Long id);
}
