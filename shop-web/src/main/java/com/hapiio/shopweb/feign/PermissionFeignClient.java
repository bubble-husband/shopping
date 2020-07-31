package com.hapiio.shopweb.feign;

import com.hapiio.pojo.model.Permission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("user-security")
public interface PermissionFeignClient {

    @RequestMapping("/permission/findAllPermission")
    public List<Permission> findAllPermission();
}
