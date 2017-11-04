package com.sp.sr.admin.controller;

import com.sp.sr.admin.SrAdminException;
import com.sp.sr.admin.service.KnowledgeService;
import com.sp.sr.model.VO.ResultVO;
import com.sp.sr.model.domain.Knowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/knowledge")
public class KnowledgeController {
    @Autowired
    private KnowledgeService knowledgeService;

    @RequestMapping("/list")
    @GetMapping
    public ResultVO<Page<Knowledge>> findAll(Integer level, @RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResultVO.ok(knowledgeService.findAllByAndLevelAndDeleteAtIsNull(new PageRequest(page, size),level));
    }
}
