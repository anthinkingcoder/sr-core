package com.sp.sr.web.topic.controller;

import com.sp.sr.model.domain.topic.TopicCategory;
import com.sp.sr.model.service.topic.TopicCategoryService;
import com.sp.sr.model.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topic/category")
public class TopicCategoryController {
    @Autowired
    private TopicCategoryService topicCategoryService;


    @GetMapping(path = "/list")
    public ResultVO<List<TopicCategory>> findAll() {
        return ResultVO.ok(topicCategoryService.findAll());
    }



    @GetMapping("/findOne")
    public ResultVO<TopicCategory> findOne(@RequestParam Long id) {
        TopicCategory topicCategory = topicCategoryService.findOne(id);
        return ResultVO.ok(topicCategory);
    }

}
