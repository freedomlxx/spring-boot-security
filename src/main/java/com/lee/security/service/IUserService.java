package com.lee.security.service;

import com.github.pagehelper.PageInfo;
import com.lee.security.entity.User;

public interface IUserService {

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     *  分页查询用户列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo listUser(int pageNum, int pageSize);
}
