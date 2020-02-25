package com.lee.security.mapper;

import com.lee.security.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    User findUserByUsername(@Param("username") String username);

    /**
     * 查询用户列表<br>
     *     配合pageHelper，不要加limit后的两个参数；否则需要加上，不然数据量太大，全部查询出来可以会内存溢出
     * @return
     */
    List<User> listUser();
}
