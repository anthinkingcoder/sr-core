package com.sp.sr.web.topic.controller;

import com.sp.sr.model.domain.topic.Topic;
import com.sp.sr.model.service.topic.TopicService;
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
@RequestMapping("/api/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;


    @RequestMapping("")
    @GetMapping
    public ResultVO<List<Topic>> findAll(@RequestParam Long categoryId) {
        return ResultVO.ok(topicService.findAllByCategoryId(categoryId));
    }

    @RequestMapping("/list")
    @GetMapping
    public ResultVO<Page<Topic>> list(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                      @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResultVO.ok(topicService.findAll(new PageRequest(page,size)));
    }

    @RequestMapping("/findOne")
    @GetMapping
    public ResultVO<Topic> findOne(@RequestParam Long topicId) {
        return ResultVO.ok(topicService.findById(topicId));
    }


    @GetMapping(path = "/search")
    public ResultVO<Page<Topic>> search(@RequestParam String search,
                                        @RequestParam(name = "page", defaultValue = "0") Integer page,
                                        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResultVO.ok(topicService.findAllByName(search, new PageRequest(page, size)));
    }
}
