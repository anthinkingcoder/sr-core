package com.sp.sr.admin.controller;

import com.sp.sr.admin.Auths;
import com.sp.sr.admin.SrAdminException;
import com.sp.sr.admin.service.KnowledgeService;
import com.sp.sr.admin.service.ResourceDocumentService;
import com.sp.sr.model.vo.ResultVO;
import com.sp.sr.model.domain.Knowledge;
import com.sp.sr.model.domain.ResourceDocument;
import com.sp.sr.model.enums.KnowledgeLevelEnum;
import com.sp.sr.model.enums.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/admin/knowledge")
@Slf4j
public class KnowledgeController extends BaseController {
    @Autowired
    private KnowledgeService knowledgeService;

    @Autowired
    private ResourceDocumentService resourceDocumentService;


    @RequestMapping("/list")
    @GetMapping
    public ResultVO<Page<Knowledge>> findAll(Integer level, @RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResultVO.ok(knowledgeService.findAllByDeleteAtIsNullAndLevel(new PageRequest(page, size), level));
    }

    @RequestMapping("/topic/list")
    @GetMapping
    public ResultVO<Page<Knowledge>> findAll(@RequestParam Long topicId,
                                             @RequestParam(name = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResultVO.ok(knowledgeService.findAllByTopicIdAndLevel(new PageRequest(page, size), KnowledgeLevelEnum.TOPIC.getState(), topicId));
    }

    @RequestMapping("/topic/all")
    @GetMapping
    public ResultVO<List<Knowledge>> findAll(@RequestParam Long topicId) {
        return ResultVO.ok(knowledgeService.findAllByTopicId(topicId));
    }

    @RequestMapping("/all")
    @GetMapping
    public ResultVO<List<Knowledge>> findAll(@RequestParam Integer level) {
        return ResultVO.ok(knowledgeService.findAllByLevel(level));
    }


    @PostMapping(path = "/save")
    public ResultVO<Knowledge> save(
            @RequestParam(required = false) Long id,
            @RequestParam(defaultValue = "1") Integer level,
            @RequestParam("name") String name,
            @RequestParam(defaultValue = "0") Integer sort,
            @RequestParam(required = false) Long topicId
    ) {
        Knowledge knowledge;
        if (!KnowledgeLevelEnum.BASE.getState().equals(level) && !KnowledgeLevelEnum.TOPIC.getState().equals(level)) {
            throw new SrAdminException(ResultStatus.ARGUMENT_ERROR);
        }
        if (id != null && id != 0) {
            knowledge = knowledgeService.findById(id);
            if (knowledge == null) {
                throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
            }
            Auths.verityUploader(USER.get(), knowledge.getUploaderId());
        } else {
            knowledge = new Knowledge();
            if (KnowledgeLevelEnum.TOPIC.getState().equals(level)) {
                if (topicId == null || topicId == 0) {
                    throw new SrAdminException(ResultStatus.ARGUMENT_ERROR);
                }
                knowledge.setTopicId(topicId);
            }
        }
        try {
            knowledge.setName(name);
            knowledge.setLevel(level);
            knowledge.setUploaderId(USER.get().getId());
            knowledge.setSort(sort);
            knowledge = knowledgeService.save(knowledge);
            return ResultVO.ok(knowledge);
        } catch (Exception e) {
            log.error("save knowledge error ,error_msg = {}", e.getMessage());
            throw new SrAdminException(ResultStatus.SERVICE_ERROR);
        }

    }

    @GetMapping(path = "/delete")
    public ResultVO<Object> delete(@RequestParam Long id) {
        Knowledge knowledge = knowledgeService.findById(id);
        if (knowledge == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        Auths.verityUploader(USER.get(), knowledge.getUploaderId());
        try {
            knowledge.setDeleteAt(new Date());
            knowledgeService.save(knowledge);
            if (knowledge.getResourceDocumentId() != null) {
                ResourceDocument resourceDocument = resourceDocumentService.findOne(knowledge.getResourceDocumentId());
                if (resourceDocument != null) {
                    resourceDocument.setDeleteAt(new Date());
                    resourceDocumentService.save(resourceDocument);
                }
            }
            return ResultVO.ok();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SrAdminException(ResultStatus.SERVICE_ERROR);
        }
    }

    @GetMapping(path = "/findOne")
    public ResultVO<Knowledge> findOne(@RequestParam Long id) {
        Knowledge knowledge = knowledgeService.findById(id);
        if (knowledge == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        Auths.verityUploader(USER.get(), knowledge.getUploaderId());
        return ResultVO.ok(knowledge);
    }
}
