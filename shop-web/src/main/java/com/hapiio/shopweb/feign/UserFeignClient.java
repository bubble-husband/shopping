package com.hapiio.shopweb.feign;

import com.hapiio.pojo.model.Permission;

import com.hapiio.shopweb.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("user-security")
public interface UserFeignClient {

    @GetMapping("/user/findUserByUsername")
    public User findUserByUsername(@RequestParam("username") String username);

    @GetMapping("/user/findPermissionByUsername")
    public List<Permission> findPermissionByUsername(@RequestParam("username") String username);
}
