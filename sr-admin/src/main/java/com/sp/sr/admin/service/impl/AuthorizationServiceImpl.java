package com.sp.sr.admin.service.impl;

import com.sp.sr.admin.SrAdminException;
import com.sp.sr.admin.service.AuthorizationService;
import com.sp.sr.admin.service.UserService;
import com.sp.sr.model.domain.User;
import com.sp.sr.model.util.Hashes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    @Autowired
    public UserService userService;


    @Override
    public User authorize(String username, String password) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            throw new SrAdminException("用户名或者密码不正确");
        }
        String salt = user.getSalt();
        if (StringUtils.isEmpty(salt)) {
            salt = Hashes.sha1(String.valueOf(new Random(System.currentTimeMillis()).nextInt(1000000)));
            user.setSalt(salt);
        }
        password = Hashes.md5(Hashes.sha1(password) + salt);
        if (StringUtils.hasText(password) && password.equals(user.getPassword())) {
            user.setLastLoginTime(new Date());
            user  = userService.saveUser(user);
            return user;
        } else {
            throw new SrAdminException("用户名或者密码不正确");
        }
    }
}
