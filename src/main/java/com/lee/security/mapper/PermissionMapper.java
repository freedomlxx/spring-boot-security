package com.lee.security.mapper;

import com.lee.security.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper {

    /**
     * 根据角色id查找权限
     * @param roleId
     * @return
     */
    List<Permission> findPermissionByRoleId(@Param("roleId") long roleId);
}
