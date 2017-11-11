package com.sp.sr.web.controller;

import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.domain.Attachment;
import com.sp.sr.model.service.AttachmentService;
import com.sp.sr.model.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhoulin
 */
@RestController
@RequestMapping("/api/attachment")
@Slf4j
public class AttachmentController extends BaseController {
    @Autowired
    private AttachmentService attachmentService;


    @RequestMapping("/module/list")
    @GetMapping
    public ResultVO<List<Attachment>> findAllByModuleCategoryAndModuleId(
                                                                         @RequestParam Integer moduleCategory,
                                                                         @RequestParam Long moduleId) {

        return ResultVO.ok(attachmentService.findAllByModuleCategoryAndModuleId(moduleCategory, moduleId));
    }

}
