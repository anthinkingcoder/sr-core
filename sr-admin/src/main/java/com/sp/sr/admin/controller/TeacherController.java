package com.sp.sr.admin.controller;

import com.sp.sr.admin.SrAdminException;
import com.sp.sr.model.domain.User;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.enums.RoleCategoryEnum;
import com.sp.sr.model.service.UserService;
import com.sp.sr.model.util.Hashes;
import com.sp.sr.model.vo.ResultVO;
import com.sp.sr.model.vo.TeacherVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author zhoulin
 */
@RestController
@RequestMapping("/api/admin/teacher")
public class TeacherController {
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    @GetMapping
    public ResultVO<Page<TeacherVO>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<User> users = userService.findAllByLevel(RoleCategoryEnum.TEACHER.getState(), new PageRequest(page, size));
        List<TeacherVO> teacherVOS = new ArrayList<>();
        TeacherVO teacherVO;
        for (User user : users.getContent()) {
            teacherVO = new TeacherVO();
            BeanUtils.copyProperties(user, teacherVO);
            teacherVOS.add(teacherVO);
        }
        Page<TeacherVO> teacherVOPage = new PageImpl<>(teacherVOS, new PageRequest(page, size), users.getTotalElements());
        teacherVO = null;
        return ResultVO.ok(teacherVOPage);
    }


    @PostMapping(path = "/save")
    public ResultVO<User> save(
            @RequestParam String name,
            @RequestParam String username,
            @RequestParam String password) {
        User user;
        user = new User();
        user.setLevel(RoleCategoryEnum.TEACHER.getState());
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

    @GetMapping(path = "/findOne")
    public ResultVO<TeacherVO> findOne(@RequestParam Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        TeacherVO teacherVO = new TeacherVO();
        BeanUtils.copyProperties(user, teacherVO);
        return ResultVO.ok(teacherVO);
    }

    @PostMapping(path = "/delete")
    public ResultVO<Object> delete(@RequestParam Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        user.setDeleteAt(new Date());
        userService.saveUser(user);
        return ResultVO.ok(user);
    }

    @PostMapping(path = "/status")
    public ResultVO<Object> updateStatus(@RequestParam Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        if (user.getStatus() == 0) {
            user.setStatus(1);
        } else {
            user.setStatus(0);
        }
        userService.saveUser(user);
        return ResultVO.ok();
    }

}
