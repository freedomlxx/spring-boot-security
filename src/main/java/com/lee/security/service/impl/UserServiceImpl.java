package com.lee.security.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lee.security.entity.User;
import com.lee.security.mapper.UserMapper;
import com.lee.security.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_MAX_PAGE_SIZE = 100;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUsername(String userName) {
        User user = userMapper.findUserByUsername(userName);
        return user;
    }

    @Override
    public PageInfo listUser(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);      // 先跟下此，看看到底做了什么
        List<User> users = userMapper.listUser();     // 调用UserMapper接口的代理，实现查询
        PageInfo pageInfo = new PageInfo(users);      // 将结果集封装进PageInfo，已经在分页之后，无需跟
        return pageInfo;
    }
}
