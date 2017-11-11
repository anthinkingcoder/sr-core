package com.sp.sr.model.service;

import com.sp.sr.model.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @author zhoulin
 */
public interface UserService {
    /**
     * find user info
     *
     * @param username user login for
     * @return user info
     */
    User findUserByUsername(String username);

    User findUserById(Long id);

    User saveUser(User user);

    Page<User> findAllByLevel(Integer level, Pageable pageable);
}
