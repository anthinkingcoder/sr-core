package com.sp.sr.admin.service.impl;

import com.sp.sr.admin.common.SrAdminException;
import com.sp.sr.admin.user.service.AuthorizationServiceImpl;
import com.sp.sr.model.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AuthorizationServiceImplTest {
    @Autowired
    public AuthorizationServiceImpl authorizationService;

    @Test
    public void authorize() throws Exception {
        try {
            User user = authorizationService.authorize("admin", "admin");
            Assert.assertNotNull(user);
            log.info("user = {}",user);
        }catch (SrAdminException e) {
            log.error(e.getMessage());
        }
    }



}