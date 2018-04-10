package com.sp.sr.web.user.service;

import com.sp.sr.admin.common.SrAdminException;
import com.sp.sr.model.domain.user.User;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.enums.RoleCategoryEnum;
import com.sp.sr.model.repository.user.UserRepository;
import com.sp.sr.model.service.user.AuthorizationService;
import com.sp.sr.model.util.Hashes;
import com.sp.sr.web.SrWebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

/**
 * @author zhoulin
 */
@Service(value = "userAuthorizationServiceImpl")
public class AuthorizationServiceImpl implements AuthorizationService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User authorize(String username, String password) {
        User user = userRepository.findUserByUsernameAndDeleteAtIsNull(username);
        if (user == null) {
            throw new SrWebException("用户名或者密码不正确");
        }
        if (!Objects.equals(user.getLevel(), RoleCategoryEnum.STUDENT.getState())) {
            throw new SrWebException(ResultStatus.AUTHORIZE_ERROR);
        }
        String salt = user.getSalt();
        if (StringUtils.isEmpty(salt)) {
            salt = Hashes.sha1(String.valueOf(new Random(System.currentTimeMillis()).nextInt(1000000)));
            user.setSalt(salt);
        }
        password = Hashes.md5(Hashes.sha1(password) + salt);
        if (StringUtils.hasText(password) && password.equals(user.getPassword())) {
            user.setLastLoginTime(new Date());
            user = userRepository.save(user);
            return user;
        } else {
            throw new SrAdminException("用户名或者密码不正确");
        }
    }
}
