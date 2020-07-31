package com.hapiio.usersecurity.service.impl;

import com.hapiio.pojo.model.Permission;
import com.hapiio.pojo.model.User;
import com.hapiio.usersecurity.mapper.UserMapper;

import com.hapiio.usersecurity.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String username){
        return userMapper.findUserByUsername(username);
    }

    @Override
    public List<Permission> findPermissionByUsername(String username){
        return userMapper.findPermissionByUsername(username);
    }
}
