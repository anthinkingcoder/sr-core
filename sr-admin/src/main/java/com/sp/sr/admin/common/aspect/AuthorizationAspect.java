package com.sp.sr.admin.common.aspect;

import com.sp.sr.admin.common.SrAdminException;
import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.domain.user.User;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.enums.RoleCategoryEnum;
import com.sp.sr.model.service.user.UserService;
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

/**
 * @author zhoulin
 */
@Aspect
@Component
@Slf4j
public class AuthorizationAspect {

    @Autowired
    private UserService userService;

    @Pointcut("execution(public * com.sp.sr.admin.*.controller.*.*(..))" +
            "&& !execution(public * com.sp.sr.admin.user.controller.AuthorizationController.*(..))" +
            "&& !execution(public * com.sp.sr.admin.common.controller.CaptchaController.*(..))")
    public void verity() {

    }

    @Pointcut("execution(public * com.sp.sr.admin.user.controller.TeacherController.*(..))" +
            "&&execution(public * com.sp.sr.admin.topic.controller.TopicCategoryController.*(..))" +
            "&&execution(public * com.sp.sr.admin.question.controller.QuestionCategoryController.*(..))" )
    public void system() {

    }

    @Pointcut("execution(public * com.sp.sr.admin.*.controller.*.*(..))" +
            "&& !execution(public * com.sp.sr.admin.user.controller.AuthorizationController.*(..)) " +
            "&& !execution(public * com.sp.sr.admin.user.controller.TeacherController.*(..))" +
            "&& !execution(public * com.sp.sr.admin.topic.controller.TopicCategoryController.*(..))" +
            "&& !execution(public * com.sp.sr.admin.question.controller.QuestionCategoryController.*(..))" +
            "&& !execution(public * com.sp.sr.admin.common.controller.CaptchaController.*(..))")
    public void admin() {

    }


    @Before("verity()")
    public void doVerity() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("adminUser");
        if (user == null || user.getStatus() == 1) {
            log.info("AUTHORIZE_ERROR");
            throw new SrAdminException(ResultStatus.AUTHORIZE_ERROR);
        }
        user = userService.findUserByUsername(user.getUsername());
        session.setAttribute("adminUser", user);
        BaseController.USER.set((User) session.getAttribute("adminUser"));
    }

    @Before("system()")
    public void doSystem() {
        if (BaseController.USER.get() == null) {
            doVerity();
        }
        if (!RoleCategoryEnum.SYSTEM.getState().equals(BaseController.USER.get().getLevel())) {
            throw new SrAdminException(ResultStatus.PERMISSION_ERROR);
        }
    }

    @Before("admin()")
    public void doAdmin() {
        if (BaseController.USER.get() == null) {
            doVerity();
        }
        if (!RoleCategoryEnum.SYSTEM.getState().equals(BaseController.USER.get().getLevel())
                && !RoleCategoryEnum.TEACHER.getState().equals(BaseController.USER.get().getLevel())) {
            throw new SrAdminException(ResultStatus.PERMISSION_ERROR);
        }
    }


}
