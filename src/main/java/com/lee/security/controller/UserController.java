package com.lee.security.controller;

import com.github.pagehelper.PageInfo;
import com.lee.security.entity.User;
import com.lee.security.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    /**
     * 获取用户信息
     * @param user
     * @return
     */
    @RequestMapping("/me")
    @ResponseBody
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/getUserName")
    @ResponseBody
    public String getUserName() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @RequestMapping("/findUserByUsername")
    @ResponseBody
    public User findUserByUsername(String username) {
        LOGGER.info("username = {}", username);
        User user = userService.findUserByUsername(username);
        return user;
    }

    @RequestMapping("/userList")
    @PreAuthorize("hasAuthority('user:view')")
    public String userList(int pageNum, int pageSize, Model model) {
        LOGGER.info("pageNum = {}, pageSize = {}", pageNum, pageSize);
        PageInfo pageInfo = userService.listUser(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);

        return "user/userList";
    }

    /**
     * 用户添加;
     * @return
     */
    @RequestMapping("/userAdd")
    @PreAuthorize("hasAuthority('user:add')")
    public String userAdd(){
        return "user/userAdd";
    }

    /**
     * 用户删除;
     * @return
     */
    @RequestMapping("/userDel")
    @PreAuthorize("hasAuthority('user:del')")
    public String userDel(){
        return "user/userDel";
    }


    public void updatePermission() {
        // 得到当前的认证信息
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //  生成当前的所有授权
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        // 添加 ROLE_VIP 授权
        updatedAuthorities.add(new SimpleGrantedAuthority("user:get"));
        // 生成新的认证信息
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
        // 重置认证信息
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }


}
