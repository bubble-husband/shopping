package com.hapiio.shopweb.service;

import com.hapiio.pojo.model.Permission;
import com.hapiio.shopweb.feign.UserFeignClient;
import com.hapiio.shopweb.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Resource
    private UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException {

        User user = userFeignClient.findUserByUsername(username);

        if (!Objects.isNull(user)){

            List<Permission> permissionList = userFeignClient.findPermissionByUsername(username);
            if (permissionList!=null&&permissionList.size()>0){
                List<GrantedAuthority>  authorities = new ArrayList<GrantedAuthority>();
                permissionList.stream().forEach(e -> {
                    authorities.add(new SimpleGrantedAuthority(e.getPermtag()));
                });
                user.setAuthorities(authorities);
            }

            return user;

        }else {
            return null;
        }


    }
}
