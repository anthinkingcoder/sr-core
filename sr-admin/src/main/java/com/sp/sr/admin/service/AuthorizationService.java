package com.sp.sr.admin.service;

import com.sp.sr.model.domain.User;

/**
 * @author zhoulin
 */
public interface AuthorizationService {
    Integer TOKEN_EXPIRE = 60 * 60 * 2;
    String TOKEN_PREFIX = "TOKEN:";


    /**
     * user authorization
     *
     * @param username username
     * @param password password
     * @return userinfo
     */
    User authorize(String username, String password);

}
