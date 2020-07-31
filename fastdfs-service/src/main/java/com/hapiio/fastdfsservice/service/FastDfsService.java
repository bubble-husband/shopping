package com.hapiio.fastdfsservice.service;

import com.hapiio.pojo.model.FastDfsFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FastDfsService {

    public FastDfsFile upload(MultipartFile multipartFile);

    public byte[] download(Long id);

    public List<FastDfsFile> findByType(Integer type);
}
