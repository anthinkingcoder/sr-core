package com.sp.sr.model.controller;

import com.sp.sr.model.domain.user.User;

/**
 * @author zhoulin
 */
public class BaseController {
    public final static ThreadLocal<User> USER = new ThreadLocal<>();
}
