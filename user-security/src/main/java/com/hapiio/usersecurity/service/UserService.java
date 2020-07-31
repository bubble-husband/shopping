package com.hapiio.usersecurity.service;

import com.hapiio.pojo.model.Permission;
import com.hapiio.pojo.model.User;

import java.util.List;

public interface UserService {

    User findUserByUsername(String username);

    List<Permission> findPermissionByUsername(String username);
}
