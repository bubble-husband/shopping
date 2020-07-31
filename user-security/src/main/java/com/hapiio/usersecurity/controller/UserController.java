package com.hapiio.usersecurity.controller;

import com.hapiio.pojo.model.Permission;
import com.hapiio.pojo.model.User;
import com.hapiio.usersecurity.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/findUserByUsername")
    User findUserByUsername(String username){
        return userService.findUserByUsername(username);
    }

    @GetMapping("/user/findPermissionByUsername")
    List<Permission> findPermissionByUsername(String username){
        return userService.findPermissionByUsername(username);
    }

}
