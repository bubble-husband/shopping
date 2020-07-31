package com.hapiio.usersecurity.service.impl;

import com.hapiio.pojo.model.Permission;
import com.hapiio.usersecurity.mapper.PermissionMapper;
import com.hapiio.usersecurity.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findAllPermission(){
        return permissionMapper.findAllPermission();
    }
}
