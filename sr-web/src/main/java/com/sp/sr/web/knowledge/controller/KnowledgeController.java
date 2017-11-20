package com.sp.sr.web.knowledge.controller;


import com.sp.sr.model.domain.knowledge.Knowledge;
import com.sp.sr.model.domain.resource.Resource;
import com.sp.sr.model.enums.KnowledgeLevelEnum;
import com.sp.sr.model.service.knowledge.KnowledgeService;
import com.sp.sr.model.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/knowledge")
public class KnowledgeController {
    @Autowired
    private KnowledgeService knowledgeService;


    @GetMapping(path = "/base/search")
    public ResultVO<Page<Knowledge>> search(@RequestParam String search,
                                           @RequestParam(name = "page", defaultValue = "0") Integer page,
                                           @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResultVO.ok(knowledgeService.findAllByNameAndLevel(search, KnowledgeLevelEnum.BASE.getState(), new PageRequest(page, size)));
    }

    @RequestMapping("/base/all")
    @GetMapping
    public ResultVO<List<Knowledge>> findBaseKnowledge() {
        return ResultVO.ok(knowledgeService.findAllByLevel(KnowledgeLevelEnum.BASE.getState()));
    }

    @RequestMapping("/topic/all")
    @GetMapping
    public ResultVO<List<Knowledge>> findTopicKnowledge(@RequestParam Long topicId) {
        return ResultVO.ok(knowledgeService.findAllByTopicId(topicId));

    }
}
