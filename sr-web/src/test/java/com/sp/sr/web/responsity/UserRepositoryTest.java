package com.sp.sr.web.responsity;

import com.sp.sr.model.repository.user.UserRepository;
import com.sp.sr.model.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindUserByUsername() throws Exception {
        User user = userRepository.findUserByUsernameAndDeleteAtIsNull("admin");
        Assert.assertNotNull(user);
        log.info(user.toString());

    }

    @Test
    public void testSave() {
        User user = new User();
        user.setCreateAt(new Date());
        user.setLastLoginTime(new Date());
        user.setName("可笑的霸王");
        user.setUsername("admin");
        user.setPassword("password");
        user.setUpdateAt(new Date());
        user.setSalt("1234");
        user = userRepository.save(user);
        Assert.assertNotNull(user);

    }

}