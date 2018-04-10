package com.sp.sr.web.knowledge.controller;

import com.sp.sr.admin.common.Auths;
import com.sp.sr.admin.common.SrAdminException;
import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.domain.knowledge.ExpandKnowledge;
import com.sp.sr.model.domain.knowledge.Knowledge;
import com.sp.sr.model.enums.KnowledgeLevelEnum;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.service.knowledge.ExpandKnowledgeService;
import com.sp.sr.model.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhoulin
 */
@RestController
@RequestMapping("/api/expand_knowledge")
@Slf4j
public class  ExpandKnowledgeController extends BaseController {
    @Autowired
    private ExpandKnowledgeService expandKnowledgeService;


    @RequestMapping("/list")
    @GetMapping
    public ResultVO<Page<ExpandKnowledge>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResultVO.ok(expandKnowledgeService.findAll(new PageRequest(page, size)));
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

    @GetMapping(path = "/search")
    public ResultVO<Page<ExpandKnowledge>> search(@RequestParam String search,
                                            @RequestParam(name = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResultVO.ok(expandKnowledgeService.findAllByName(search, new PageRequest(page, size)));
    }
}
