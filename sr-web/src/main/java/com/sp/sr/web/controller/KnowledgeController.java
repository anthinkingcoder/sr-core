package com.sp.sr.web.controller;


import com.sp.sr.model.domain.Knowledge;
import com.sp.sr.model.enums.KnowledgeLevelEnum;
import com.sp.sr.model.service.KnowledgeService;
import com.sp.sr.model.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
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
