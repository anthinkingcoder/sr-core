package com.sp.sr.admin.user.controller;

import com.google.code.kaptcha.Constants;
import com.sp.sr.admin.common.SrAdminException;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.service.user.AuthorizationService;
import com.sp.sr.model.vo.ResultVO;
import com.sp.sr.model.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhoulin
 */
@RestController
@RequestMapping("/api/admin")
public class AuthorizationController {
    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping("/login")
    @PostMapping
    public ResultVO<Object> login(String username,
                                  String password,
                                  String captcha,
                                  HttpServletRequest request) {
        User user = authorizationService.authorize(username, password);
        HttpSession session = request.getSession();
        String kcaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (!Objects.equals(captcha,kcaptcha)) {
            throw new SrAdminException(ResultStatus.CAPTCHA_ERROR);
        }
        if (user.getStatus() == 1) {
            throw new SrAdminException(ResultStatus.ACCOUNT_ERROR);
        }
        session.setAttribute("adminUser", user);
        Map<String, Object> args = new HashMap<>(15);
        args.put("name", user.getName());
        return ResultVO.ok(args);
    }
}
