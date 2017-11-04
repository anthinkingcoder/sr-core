package com.sp.sr.admin.service;

import com.sp.sr.model.domain.User;

/**
 * @author zhoulin
 */
public interface UserService {
    /**
     * find user info
     * @param username user login for
     * @return user info
     */
    User findUserByUsername(String username);
    User saveUser(User user);
}
