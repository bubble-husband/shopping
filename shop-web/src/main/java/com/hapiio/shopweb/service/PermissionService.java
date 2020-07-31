package com.hapiio.shopweb.service;

import com.hapiio.pojo.model.Permission;
import com.hapiio.shopweb.feign.PermissionFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionFeignClient permissionFeignClient;

    public List<Permission> findAllPermission(){
        return permissionFeignClient.findAllPermission();
    }
}
