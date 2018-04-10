package com.sp.sr.web.work.controller;

import com.sp.sr.admin.common.SrAdminException;
import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.domain.knowledge.Knowledge;
import com.sp.sr.model.domain.work.StudentWork;
import com.sp.sr.model.enums.KnowledgeLevelEnum;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.service.work.StudentWorkService;
import com.sp.sr.model.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhoulin
 */
@RestController
@RequestMapping("/api/student_work")
public class StudentWorkController extends BaseController {
    @Autowired
    private StudentWorkService studentWorkService;

    @RequestMapping("")
    @GetMapping
    public ResultVO<Page<StudentWork>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size, Integer category) {
        if (category == null || category == 0) {
            return ResultVO.ok(studentWorkService.findAll(new PageRequest(page, size)));
        } else {
            return ResultVO.ok(studentWorkService.findAllByCategory(new PageRequest(page, size), category));
        }
    }


    @GetMapping(path = "/detail")
    public ResultVO<StudentWork> findOne(@RequestParam Long id) {
        StudentWork studentWork = studentWorkService.findOne(id);
        if (studentWork == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        return ResultVO.ok(studentWork);
    }


    @GetMapping(path = "/search")
    public ResultVO<Page<StudentWork>> search(@RequestParam String search,
                                              @RequestParam(name = "page", defaultValue = "0") Integer page,
                                              @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResultVO.ok(studentWorkService.findAllByTitle(search, new PageRequest(page, size)));
    }
}
