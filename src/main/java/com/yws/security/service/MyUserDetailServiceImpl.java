package com.yws.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author weisen.yang@hand-china.com
 * @Date 2017/11/21 20:02
 * @name
 * @description  登陆验证时，通过username获取用户的所有权限信息
 *                 可以从数据库中读入用户的密码，角色信息，是否锁定，账号是否过期等
 */
public class MyUserDetailServiceImpl implements UserDetailsService{
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

        SimpleGrantedAuthority auth2 = new SimpleGrantedAuthority("ROLE_ADMIN");
        SimpleGrantedAuthority auth1 = new SimpleGrantedAuthority("ROLE_USER");
//        GrantedAuthorityImpl auth2=new GrantedAuthorityImpl("ROLE_ADMIN");
//        GrantedAuthorityImpl auth1=new GrantedAuthorityImpl("ROLE_USER");

        if(username.equals("admin")){
            auths.add(auth1);
            auths.add(auth2);
        }
        if("user".equals(username)){
            auths.add(auth1);
        }

        User user = new User(username, "123456", true, true, true, true, auths);
        return user;
    }
}
