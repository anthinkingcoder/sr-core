package com.sp.sr.admin.service.impl;

import com.sp.sr.admin.reponsity.UserRepository;
import com.sp.sr.admin.service.UserService;
import com.sp.sr.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author zhoulin
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsernameAndDeleteAtIsNull(username);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findByIdAndDeleteAtIsNull(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Page<User> findAllByLevel(Integer level, Pageable pageable) {
        return userRepository.findAllByLevelAndDeleteAtIsNull(level, pageable);
    }
}

