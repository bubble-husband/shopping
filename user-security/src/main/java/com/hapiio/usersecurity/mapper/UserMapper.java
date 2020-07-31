package com.hapiio.usersecurity.mapper;


import com.hapiio.pojo.model.Permission;
import com.hapiio.pojo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {


    //根据用户账号查询
    @Select("select * from sys_user where username=#{username}")
    User findUserByUsername(String username);

    // 查询用户的权限
    @Select(" select permission.* from sys_user user" + " inner join sys_user_role user_role"
            + " on user.id = user_role.user_id inner join "
            + "sys_role_permission role_permission on user_role.role_id = role_permission.role_id "
            + " inner join sys_permission permission on role_permission.perm_id = permission.id where user.username = #{username};")
    List<Permission> findPermissionByUsername(@Param("username") String username);
}
