package com.hapiio.shopweb.config;


import com.hapiio.pojo.model.Permission;
import com.hapiio.shopweb.handler.MyAuthenticationFailureHandler;
import com.hapiio.shopweb.handler.MyAuthenticationSuccessHandler;
import com.hapiio.shopweb.service.MyUserDetailService;
import com.hapiio.shopweb.service.PermissionService;
import com.hapiio.shopweb.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    //用户认证身份
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
       auth.userDetailsService(myUserDetailService).passwordEncoder(new PasswordEncoder() {
            //对表单密码进行加密
            @Override
            public String encode(CharSequence rawPassword) {
                System.out.println("password-----:"+rawPassword);
                return MD5Util.encode(rawPassword.toString());
            }

            //加密的密码与数据库的密码进行比对

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                System.out.println("-----------");
                System.out.println("rawPassword:"+rawPassword+"------encodedPassword:"+encodedPassword);
                return MD5Util.encode(rawPassword.toString()).equals(encodedPassword);
            }
        });
    }

    //用户权限配置
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = httpSecurity.authorizeRequests();
        List<Permission> permissions = permissionService.findAllPermission();
        if (permissions!=null &&permissions.size()>0){
            permissions.stream().forEach(permission -> {
                //设置权限
                authorizeRequests.antMatchers(permission.getUrl()).hasAnyAuthority(permission.getPermtag());
            });
        }
        authorizeRequests.antMatchers("/login").permitAll()
                .antMatchers("/js/**","/css/**","/plugins/**","/img/**").permitAll()
//                .antMatchers("/**").hasAnyRole("/business")
                .antMatchers("/**").fullyAuthenticated()
                .and().headers().frameOptions().disable()
                .and().formLogin().loginPage("/login")
                .successHandler(myAuthenticationSuccessHandler).failureHandler(myAuthenticationFailureHandler)
                .and().csrf().disable()
                .logout().logoutSuccessUrl("/login");


//        httpSecurity.authorizeRequests()
//                .antMatchers("/addOrder").hasAnyAuthority("addOrder")
//                .antMatchers("/selectOrder").hasAnyAuthority("selectOrder")
//                .antMatchers("/deleteOrder").hasAnyAuthority("deleteOrder")
//                .antMatchers("/updateOrder").hasAnyAuthority("updateOrder")
//                .antMatchers("/login").permitAll()
//                .antMatchers("/**").fullyAuthenticated().and().formLogin().loginPage("/login")
//                .and().csrf().disable();

    }

}
