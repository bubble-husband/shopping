package com.hapiio.fastdfsservice.service.impl;

import com.hapiio.fastdfsservice.mapper.FastDfsFileMapper;
import com.hapiio.fastdfsservice.service.FastDfsService;
import com.hapiio.pojo.model.FastDfsFile;
import com.luhuiguo.fastdfs.domain.StorePath;
import com.luhuiguo.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class FastDfsServiceImpl implements FastDfsService {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Resource
    private FastDfsFileMapper fastDfsFileMapper;

    @Override
    public FastDfsFile upload(MultipartFile multipartFile) {
        //上传到哪一个group1
        //上传文件的扩展名
        //上传后返回的地址是什么
        FastDfsFile fastDfsFile = null;
        try {
            StorePath storePath = null;
            //文件名：上传的时候的文件名
            String fileName = multipartFile.getOriginalFilename();
            //上传时的扩展名
            String extendName = FilenameUtils.getExtension(fileName);
            //保存到fastdfs
            storePath = fastFileStorageClient.uploadFile("group1", multipartFile.getBytes(), extendName);

            System.out.println(storePath.getFullPath());
            //保存到数据库上面去
            fastDfsFile = new FastDfsFile(fileName, "http://192.168.126.22:8090", "group1", storePath.getFullPath(), new Date());

            fastDfsFileMapper.add(fastDfsFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fastDfsFile;
    }

    @Override
    public byte[] download(Long id) {
        //根据id查找文件名
        FastDfsFile fastDfsFile = fastDfsFileMapper.findOne(id);
        //从fastdfs上下载数据流
        byte[] bytes = fastFileStorageClient.downloadFile(fastDfsFile.getGroupname(), fastDfsFile.getPath().substring(fastDfsFile.getPath().indexOf("/") + 1));

        return bytes;


    }


    @Override
    public List<FastDfsFile> findByType(Integer type) {
        return fastDfsFileMapper.findByType(type);
    }
}
