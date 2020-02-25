package com.lee.security.handler;

import com.lee.security.entity.Role;
import com.lee.security.entity.User;
import com.lee.security.mapper.PermissionMapper;
import com.lee.security.mapper.RoleMapper;
import com.lee.security.mapper.UserMapper;
import com.lee.security.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sang on 2017/12/28.
 */
@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findUserByUsername(s);
        if (null == user) {
            throw new UsernameNotFoundException(s);
        }
        List<Role> roles = roleMapper.findRoleByUsername(user.getUsername());
        log.info("用户：{}, 角色有{}个", user.getUsername(), roles.size());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.stream().forEach(
                role -> {
                    permissionMapper.findPermissionByRoleId(role.getId()).stream().forEach(  // 添加角色拥有的权限
                            permission -> authorities.add(new SimpleGrantedAuthority(permission.getPermission()))
                    );
                }
        );
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

}
