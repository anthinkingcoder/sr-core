package com.sp.sr.admin.controller;

import com.sp.sr.admin.SrAdminException;
import com.sp.sr.admin.service.AuthorizationService;
import com.sp.sr.model.VO.ResultVO;
import com.sp.sr.model.domain.User;
import com.sp.sr.model.enums.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/user")
/**
 * @author zhoulin
 */
public class UserController {


    @RequestMapping("/userinfo")
    @GetMapping
    public ResultVO<Object> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return ResultVO.ok(user);
    }

    @RequestMapping("/logout")
    @GetMapping
    public ResultVO<Object> logout(HttpSession session) {
        session.removeAttribute("user");
        return ResultVO.ok();

    }
}
