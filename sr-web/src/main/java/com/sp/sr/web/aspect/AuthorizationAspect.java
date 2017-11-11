package com.sp.sr.web.aspect;

import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.domain.User;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.enums.RoleCategoryEnum;
import com.sp.sr.model.service.UserService;
import com.sp.sr.web.SrWebException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author zhoulin
 */
@Aspect
@Component
@Slf4j
public class AuthorizationAspect {

    @Autowired
    private UserService userService;

    @Pointcut("execution(public * com.sp.sr.web..controller.*.*(..))" +
            "&& !execution(public * com.sp.sr.web.controller.AuthorizationController.*(..)) && !execution(public * com.sp.sr.web.controller.UserController.register(..))")
    public void verity() {

    }




    @Before("verity()")
    public void doVerity() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new SrWebException(ResultStatus.AUTHORIZE_ERROR);
        }
        if (!Objects.equals(user.getLevel(), RoleCategoryEnum.STUDENT.getState())) {
            throw new SrWebException(ResultStatus.AUTHORIZE_ERROR);
        }
        user = userService.findUserByUsername(user.getUsername());
        session.setAttribute("user", user);
        BaseController.USER.set((User) session.getAttribute("user"));

    }



}
