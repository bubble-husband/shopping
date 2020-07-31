package com.hapiio.usersecurity.controller;

import com.hapiio.pojo.model.Permission;
import com.hapiio.usersecurity.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/permission/findAllPermission")
    List<Permission> findAllPermission(){
        return permissionService.findAllPermission();
    }
}
