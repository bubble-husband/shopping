package com.hapiio.shopweb.controller;

import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.FastDfsFile;
import com.hapiio.shopweb.feign.FastDfsFileFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@RestController
@RequestMapping("/fastdfs")
public class FastDfsFileController {

    @Autowired
    private FastDfsFileFeignClient fastDfsFileFeignClient;

    @PostMapping("/upload")
    public FastDfsFile upload(@RequestPart(value = "file",required = false) MultipartFile multipartFile){
        return fastDfsFileFeignClient.upload(multipartFile);
    }

    @GetMapping("/download/{id}")
    public Result download(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println(id);
        Result result = null;
        byte[] bytes = null;
        try {
            bytes = fastDfsFileFeignClient.download(id);
//            FileOutputStream fileOutputStream = new FileOutputStream(new File("i:/lg.jpg"));
//            fileOutputStream.write(bytes);
            response.setHeader("Content-Disponsition","attachment;Filename="+ URLEncoder.encode("asd","UTF-8"));
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
            result = new Result(true,"下载成功");
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false, "下载失败");
        }
        return result;
    }

}
