package com.hapiio.usersecurity.service;

import com.hapiio.pojo.model.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAllPermission();
}
