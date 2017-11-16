package com.sp.sr.admin.user.controller;

import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.vo.ResultVO;
import com.sp.sr.model.domain.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admin/user")
/**
 * @author zhoulin
 */
public class UserController {


    @RequestMapping("/userinfo")
    @GetMapping
    public ResultVO<Object> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute("adminUser");
        return ResultVO.ok(user);
    }

    @RequestMapping("/logout")
    @GetMapping
    public ResultVO<Object> logout(HttpSession session) {
        session.removeAttribute("adminUser");
        BaseController.USER.remove();
        return ResultVO.ok();
    }
}
