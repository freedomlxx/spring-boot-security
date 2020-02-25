package com.lee.security.mapper;

import com.lee.security.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    List<Role> findRoleByUsername(@Param("username") String username);
}
