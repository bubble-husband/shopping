package com.hapiio.managerweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.inMemoryAuthentication().withUser("admin").password("123456").authorities("ROLE_ADMIN");
        authenticationManagerBuilder.inMemoryAuthentication().withUser("user").password("123456").authorities("ROLE_USER");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity)throws Exception{

        httpSecurity.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/js/**","/css/**","/plugins/**","/img/**").permitAll()
                .antMatchers("/**").fullyAuthenticated()
                .and().headers().frameOptions().disable()
                .and().formLogin().loginPage("/login")
                .and().csrf().disable()
                .logout().logoutSuccessUrl("/login");
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder(){
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}
