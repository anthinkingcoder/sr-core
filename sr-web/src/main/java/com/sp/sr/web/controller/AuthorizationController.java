package com.sp.sr.web.controller;

import com.sp.sr.model.domain.User;
import com.sp.sr.model.service.AuthorizationService;
import com.sp.sr.model.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoulin
 */
@RestController(value = "userAuthorizationController")
@RequestMapping("/api")
public class AuthorizationController {
    @Autowired
    private AuthorizationService authorizationService;
    @RequestMapping("/login")
    @PostMapping
    public ResultVO<Object> login(String username, String password, HttpServletRequest request) {
        User user = authorizationService.authorize(username, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        Map<String, Object> args = new HashMap<>(15);
        args.put("name", user.getName());
        return ResultVO.ok(args);
    }
}
