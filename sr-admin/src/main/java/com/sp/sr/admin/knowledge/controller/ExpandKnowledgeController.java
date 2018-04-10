package com.sp.sr.admin.knowledge.controller;

import com.sp.sr.admin.common.Auths;
import com.sp.sr.admin.common.SrAdminException;
import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.service.knowledge.ExpandKnowledgeService;
import com.sp.sr.model.vo.ResultVO;
import com.sp.sr.model.domain.knowledge.ExpandKnowledge;
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
@RequestMapping("/api/admin/expand_knowledge")
@Slf4j
public class ExpandKnowledgeController extends BaseController {
    @Autowired
    private ExpandKnowledgeService expandKnowledgeService;


    @RequestMapping("/list")
    @GetMapping
    public ResultVO<Page<ExpandKnowledge>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResultVO.ok(expandKnowledgeService.findAll(new PageRequest(page, size)));
    }


    @PostMapping(path = "/save")
    public ResultVO<ExpandKnowledge> save(
            @RequestParam(required = false) Long id,
            @RequestParam("name") String name,
            @RequestParam("url") String url,
            @RequestParam(required = false) String coverUrl,
            @RequestParam String summary,
            @RequestParam String content
    ) {
        ExpandKnowledge expandKnowledge;
        if (id != null && id != 0) {
            expandKnowledge = expandKnowledgeService.findById(id);
            if (expandKnowledge == null) {
                throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
            }
            Auths.verityUploader(USER.get(), expandKnowledge.getUploaderId());
        } else {
            expandKnowledge = new ExpandKnowledge();
            expandKnowledge.setUploaderId(USER.get().getId());
        }
        try {
            expandKnowledge.setName(name);
            expandKnowledge.setSummary(summary);
            expandKnowledge.setUrl(url);
            expandKnowledge.setCoverUrl(coverUrl);
            expandKnowledge.setUploaderId(USER.get().getId());
            expandKnowledge.setContent(content);
            expandKnowledge = expandKnowledgeService.save(expandKnowledge);
            return ResultVO.ok(expandKnowledge);
        } catch (Exception e) {
            log.error("save knowledge error ,error_msg = {}", e.getMessage());
            throw new SrAdminException(ResultStatus.SERVICE_ERROR);
        }

    }

    @PostMapping(path = "/delete")
    public ResultVO<Object> delete(@RequestParam Long id) {
        ExpandKnowledge expandKnowledge = expandKnowledgeService.findById(id);
        if (expandKnowledge == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        Auths.verityUploader(USER.get(), expandKnowledge.getUploaderId());
        try {
            expandKnowledge.setDeleteAt(new Date());
            expandKnowledgeService.save(expandKnowledge);
            return ResultVO.ok();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SrAdminException(ResultStatus.SERVICE_ERROR);
        }
    }

    @GetMapping(path = "/findOne")
    public ResultVO<ExpandKnowledge> findOne(@RequestParam Long id) {
        ExpandKnowledge expandKnowledge = expandKnowledgeService.findById(id);
        if (expandKnowledge == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        Auths.verityUploader(USER.get(), expandKnowledge.getUploaderId());
        return ResultVO.ok(expandKnowledge);
    }
}
