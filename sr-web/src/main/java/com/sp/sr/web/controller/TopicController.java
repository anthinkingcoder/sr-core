package com.sp.sr.web.controller;

import com.sp.sr.model.domain.Topic;
import com.sp.sr.model.service.TopicService;
import com.sp.sr.model.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;


    @RequestMapping("")
    @GetMapping
    public ResultVO<List<Topic>> findAll(@RequestParam Long categoryId) {
        return ResultVO.ok(topicService.findAllByCategoryId(categoryId));
    }
}
