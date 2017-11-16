package com.sp.sr.admin.work.controller;

import com.sp.sr.admin.common.Auths;
import com.sp.sr.admin.common.SrAdminException;
import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.domain.work.StudentWork;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.enums.StudentWorkCategoryEnum;
import com.sp.sr.model.service.StudentWorkService;
import com.sp.sr.model.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author zhoulin
 */
@RestController
@RequestMapping("/api/admin/student_work")
public class StudentWorkController extends BaseController {
    @Autowired
    private StudentWorkService studentWorkService;

    @RequestMapping("/list")
    @GetMapping
    public ResultVO<Page<StudentWork>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResultVO.ok(studentWorkService.findAll(new PageRequest(page, size)));
    }

    @PostMapping(path = "/save")
    public ResultVO<StudentWork> save(
            @RequestParam(required = false) Long id,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String author,
            @RequestParam String summary,
            @RequestParam Integer category) {

        if (!StudentWorkCategoryEnum.exists(category)) {
            throw new SrAdminException(ResultStatus.ARGUMENT_ERROR);
        }
        StudentWork studentWork;
        if (id != null && id != 0) {
            studentWork = studentWorkService.findOne(id);
            if (studentWork == null) {
                throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
            }
            Auths.verityUploader(USER.get(), studentWork.getUploaderId());
            studentWork.setUpdateAt(new Date());
        } else {
            studentWork = new StudentWork();
            studentWork.setUploaderId(USER.get().getId());
        }
        studentWork.setTitle(title);
        studentWork.setContent(content);
        studentWork.setAuthor(author);
        studentWork.setCategory(category);
        studentWork.setSummary(summary);
        studentWork = studentWorkService.save(studentWork);
        return ResultVO.ok(studentWork);

    }

    @PostMapping(path = "/delete")
    public ResultVO<StudentWork> delete(@RequestParam Long id) {
        StudentWork studentWork = studentWorkService.findOne(id);
        if (studentWork == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        Auths.verityUploader(USER.get(), studentWork.getUploaderId());
        studentWork.setDeleteAt(new Date());
        studentWorkService.save(studentWork);
        return ResultVO.ok(studentWork);
    }

    @GetMapping(path = "/findOne")
    public ResultVO<StudentWork> findOne(@RequestParam Long id) {
        StudentWork studentWork = studentWorkService.findOne(id);
        if (studentWork == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        Auths.verityUploader(USER.get(), studentWork.getUploaderId());
        return ResultVO.ok(studentWork);
    }
}
