package com.dong.springsecurity.config;

import com.dong.springsecurity.model.SelfUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author caishaodong
 * @Date 2020-11-01 16:00
 * @Description
 **/
@Component
public class SelfUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名获取到用户信息，以及用户密码信息
        String password = "123456";
        password = new BCryptPasswordEncoder().encode(password);

        //构建用户信息的逻辑(取数据库/LDAP等用户信息)
        UserDetails userDetails = null;
        if (username.equals("admin")) {
            userDetails = getUserDetails(username, password);
        } else {
            userDetails = getUserDetails2(username, password);
        }
        return userDetails;
    }

    /**
     * 获取 UserDetails 方法一
     *
     * @param username
     * @param password
     * @return
     */
    public UserDetails getUserDetails(String username, String password) {
        SelfUserDetails selfUserDetails = new SelfUserDetails();
        selfUserDetails.setUsername(username);
        selfUserDetails.setPassword(password);

        Set<GrantedAuthority> authList = getAuthorities();
        selfUserDetails.setAuthorities(authList);
        return selfUserDetails;
    }

    /**
     * 获取 UserDetails 方法二
     *
     * @param username
     * @param password
     * @return
     */
    public UserDetails getUserDetails2(String username, String password) {
        Collection<GrantedAuthority> authList = getAuthorities();
        UserDetails userDetails = new User(username, password, true, true, true, true, authList);
        return userDetails;
    }

    /**
     * 获取权限
     *
     * @return
     */
    private Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authList = new HashSet<>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authList;
    }
}
