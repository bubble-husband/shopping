package com.hapiio.redisservice.service.impl;

import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.Content;
import com.hapiio.redisservice.service.RedisListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisListServiceImpl implements RedisListService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Result save(String key, List<Content> contents){
        try{
            for (int i = 0; i < contents.size(); i++) {
                Content content= contents.get(i);
                redisTemplate.boundListOps(key).leftPush(content);

            }
            return new Result(true,"成功写入Redis");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"写入Redis失败");
        }
    }

    @Override
    public List<Object> find(String key){
        List list= redisTemplate.boundListOps(key).range(0,redisTemplate.boundListOps(key).size());
        return list;
    }
}
