package com.sp.sr.admin.service.impl;

import com.sp.sr.admin.user.service.UserServiceImpl;
import com.sp.sr.model.domain.user.User;
import com.sp.sr.model.util.Hashes;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void findUserByUsername() throws Exception {
    }

    @Test
    public void saveUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setLevel(1);
        String salt = Hashes.sha1(String.valueOf(new Random(System.currentTimeMillis()).nextInt(1000000)));
        user.setSalt(salt);
        String password = Hashes.md5(Hashes.sha1("admin") + salt);
        user.setPassword(password);
        user.setCreateAt(new Date());
        user.setName("可笑的霸王");
        user.setUpdateAt(new Date());
        user = userService.saveUser(user);
        Assert.assertNotNull(user);

    }

}