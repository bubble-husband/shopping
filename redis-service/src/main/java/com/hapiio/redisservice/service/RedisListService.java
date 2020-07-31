package com.hapiio.redisservice.service;

import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.Content;

import java.util.List;

public interface RedisListService {


    Result save(String key, List<Content> contents);

    List<Object> find(String key);
}
