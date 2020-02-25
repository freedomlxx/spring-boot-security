package com.lee.security.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户名
     */
    private String username;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 用户状态，1：可用，0：不可用
     */
    private Integer state;
    /**
     * 描述
     */
    private String description;

}
