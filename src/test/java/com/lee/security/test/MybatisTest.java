package com.lee.security.test;

import com.lee.security.SecurityApplication;
import com.lee.security.entity.User;
import com.lee.security.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityApplication.class)
public class MybatisTest {

    @Autowired
    private IUserService userService;

    @Test
    public void testFindUserByUsername() {
        User user = userService.findUserByUsername("brucelee");
        Assert.assertEquals(user.getDescription(), "龙的传人");
    }
}
