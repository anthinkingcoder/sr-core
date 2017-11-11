package com.sp.sr.web.controller;

import com.sp.sr.admin.SrAdminException;
import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.domain.User;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.enums.RoleCategoryEnum;
import com.sp.sr.model.service.UserService;
import com.sp.sr.model.util.Hashes;
import com.sp.sr.model.vo.ResultVO;
import com.sp.sr.web.SrWebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Random;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResultVO<User> register(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String name) {
        User user = userService.findUserByUsername(username);
        if (user != null) {
            throw new SrWebException(ResultStatus.RESOURCE_REPEAT);
        }
        user = new User();
        user.setLevel(RoleCategoryEnum.STUDENT.getState());
        String salt = Hashes.sha1(String.valueOf(new Random(System.currentTimeMillis()).nextInt(1000000)));
        user.setSalt(salt);
        password = Hashes.md5(Hashes.sha1(password) + salt);
        user.setPassword(password);
        user.setName(name);
        user.setUsername(username);
        user.setStatus(0);
        user = userService.saveUser(user);
        return ResultVO.ok(user);
    }

    @RequestMapping("/logout")
    @GetMapping
    public ResultVO<Object> logout(HttpSession session) {
        session.removeAttribute("user");
        BaseController.USER.remove();
        return ResultVO.ok();
    }

    @GetMapping("/info")
    public ResultVO<User> getUserInfo() {
        return ResultVO.ok(BaseController.USER.get());
    }
}
