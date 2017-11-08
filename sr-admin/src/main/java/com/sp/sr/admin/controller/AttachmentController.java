package com.sp.sr.admin.controller;

import com.sp.sr.admin.Auths;
import com.sp.sr.admin.SrAdminException;
import com.sp.sr.admin.service.AttachmentService;
import com.sp.sr.admin.service.ExampleService;
import com.sp.sr.admin.service.KnowledgeService;
import com.sp.sr.model.vo.ResultVO;
import com.sp.sr.model.domain.Attachment;
import com.sp.sr.model.domain.Knowledge;
import com.sp.sr.model.domain.example.Example;
import com.sp.sr.model.enums.ModuleCategoryEnum;
import com.sp.sr.model.enums.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author zhoulin
 */
@RestController
@RequestMapping("/api/admin/attachment")
@Slf4j
public class AttachmentController extends BaseController {
    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private KnowledgeService knowledgeService;

    @Autowired
    private ExampleService exampleService;


    @RequestMapping("/module/list")
    @GetMapping
    public ResultVO<List<Attachment>> findAllByModuleCategoryAndModuleId(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                         @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                         @RequestParam Integer moduleCategory,
                                                                         @RequestParam Long moduleId) {


        return ResultVO.ok(attachmentService.findAllByModuleCategoryAndModuleId(moduleCategory, moduleId));
    }

    @PostMapping(path = "/save")
    public ResultVO<Attachment> save(
            @RequestParam(required = false) Long id,
            @RequestParam Integer moduleCategory,
            @RequestParam Long moduleId,
            @RequestParam String url,
            @RequestParam String name,
            @RequestParam(defaultValue = "0") Integer sort) {
        if (ModuleCategoryEnum.DOCUMENT.getState().equals(moduleCategory)) {
            Knowledge knowledge = knowledgeService.findById(moduleId);
            if (knowledge == null) {
                throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
            }
        } else if (ModuleCategoryEnum.EXAMPLE.getState().equals(moduleCategory)) {
            Example example = exampleService.findById(moduleId);
            if (example == null) {
                throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
            }
        } else if (ModuleCategoryEnum.STUDENT_WORK.getState().equals(moduleCategory)) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        } else {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        Attachment attachment;
        if (id != null && id != 0) {
            attachment = attachmentService.findOne(id);
            if (attachment == null) {
                throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
            }
            Auths.verityUploader(USER.get(), attachment.getUploaderId());
        } else {
            attachment = new Attachment();
            attachment.setUploaderId(USER.get().getId());
        }
        attachment.setUrl(url);
        attachment.setSort(sort);
        attachment.setModuleId(moduleId);
        attachment.setModuleCategory(moduleCategory);
        attachment.setName(name);
        attachment = attachmentService.save(attachment);
        return ResultVO.ok(attachment);
    }

    @GetMapping(path = "/findOne")
    public ResultVO<Attachment> findOne(@RequestParam Long id) {
        Attachment attachment = attachmentService.findOne(id);
        if (attachment == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        Auths.verityUploader(USER.get(), attachment.getUploaderId());
        return ResultVO.ok(attachment);
    }

    @PostMapping(path = "/delete")
    public ResultVO<Attachment> delete(@RequestParam Long id) {
        Attachment attachment = attachmentService.findOne(id);
        if (attachment == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        Auths.verityUploader(USER.get(), attachment.getUploaderId());
        try {
            attachment.setDeleteAt(new Date());
            attachmentService.save(attachment);
            return ResultVO.ok(attachment);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SrAdminException(ResultStatus.SERVICE_ERROR);
        }
    }

}
