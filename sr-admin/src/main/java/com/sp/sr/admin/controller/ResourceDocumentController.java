package com.sp.sr.admin.controller;

import com.sp.sr.admin.Auths;
import com.sp.sr.admin.SrAdminException;
import com.sp.sr.admin.service.KnowledgeService;
import com.sp.sr.admin.service.ResourceDocumentService;
import com.sp.sr.model.enums.RoleCategoryEnum;
import com.sp.sr.model.vo.ResultVO;
import com.sp.sr.model.domain.Knowledge;
import com.sp.sr.model.domain.ResourceDocument;
import com.sp.sr.model.enums.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author zhoulin
 */
@RestController
@RequestMapping("/api/admin/resource_document")
@Slf4j
public class ResourceDocumentController extends BaseController {
    @Autowired
    private ResourceDocumentService resourceDocumentService;

    @Autowired
    private KnowledgeService knowledgeService;

    @RequestMapping("/list")
    @GetMapping
    public ResultVO<Page<ResourceDocument>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResultVO.ok(resourceDocumentService.findAll(new PageRequest(page, size)));
    }

    @PostMapping(path = "/save")
    public ResultVO<ResourceDocument> save(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long knowledgeId,
            @RequestParam("content") String content) {
        Knowledge knowledge = knowledgeService.findById(knowledgeId);
        if (knowledge == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }

        ResourceDocument resourceDocument;
        if (id != null && id != 0) {

            resourceDocument = resourceDocumentService.findOne(id);
            if (resourceDocument == null) {
                throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
            }
            Auths.verityUploader(USER.get(), resourceDocument.getUploaderId());
            resourceDocument.setUpdateAt(new Date());
        } else {
            if (knowledge.getResourceDocumentId() != null) {
                throw new SrAdminException(ResultStatus.REPEAT_SAVE);
            }

            resourceDocument = new ResourceDocument();
            resourceDocument.setUploaderId(USER.get().getId());
        }
        resourceDocument.setContent(content);
        resourceDocument = resourceDocumentService.save(resourceDocument);

        if (knowledge.getResourceDocumentId() == null) {
            knowledge.setResourceDocumentId(resourceDocument.getId());
            knowledgeService.save(knowledge);
        }

        return ResultVO.ok(resourceDocument);

    }
    @GetMapping(path = "/findOne")
    public ResultVO<ResourceDocument> findOne(@RequestParam Long id) {
        ResourceDocument resourceDocument = resourceDocumentService.findOne(id);
        if (resourceDocument == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        Auths.verityUploader(USER.get(), resourceDocument.getUploaderId());
        return ResultVO.ok(resourceDocument);
    }


}
