package com.sp.sr.admin.controller;

import com.sp.sr.model.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

/**
 * @author zhoulin
 */
@Controller
public class BaseController {
    public final static ThreadLocal<User> USER = new ThreadLocal<>();


}
