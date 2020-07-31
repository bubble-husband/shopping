package com.hapiio.managerweb.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/")
    public String index(){
        return "admin/index";
    }

    @RequestMapping("/loginName")
    public Map<String,String> loginName(){
        Map<String ,String > map =new HashMap<>();
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        map.put("loginName",loginName);
        return map;

    }
}
