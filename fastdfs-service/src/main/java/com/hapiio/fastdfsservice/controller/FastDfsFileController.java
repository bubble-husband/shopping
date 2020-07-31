package com.hapiio.fastdfsservice.controller;

import com.hapiio.fastdfsservice.service.FastDfsService;
import com.hapiio.pojo.model.FastDfsFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/fastdfs")
public class FastDfsFileController {

    @Autowired
    private FastDfsService fastDfsService;

    @PostMapping("/upload")
    public FastDfsFile upload(@RequestPart(value = "file",required = false) MultipartFile multipartFile){
        return fastDfsService.upload(multipartFile);
    }

    @GetMapping("/download")
    public byte[] download(@RequestParam("id") Long id){
        return fastDfsService.download(id);
    }

    @GetMapping("/findByType")
    public List<FastDfsFile> findByType(Integer type){
        return fastDfsService.findByType(type);
    }

}
