package org.eeasonloo.security;

import com.alibaba.dubbo.config.annotation.Reference;
import org.eeasonloo.pojo.Permission;
import org.eeasonloo.pojo.Role;
import org.eeasonloo.pojo.User;
import org.eeasonloo.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Component
public class SpringSecurityUserService implements UserDetailsService {

    @Reference
    private UserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //远程调用用户服务，根据用户名查询用户信息
        User user = userService.findByUsername(username);
        if (user == null) {
            //用户名不存在
            return null;
        }
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            //授予角色
            list.add(new SimpleGrantedAuthority(role.getKeyword()));
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                //授权
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }

        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(), list);
        return userDetails;
    }

}

