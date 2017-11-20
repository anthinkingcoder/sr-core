package com.sp.sr.web.example.controller;

import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.domain.example.Example;
import com.sp.sr.model.dto.ExampleDTO;
import com.sp.sr.model.service.example.ExampleService;
import com.sp.sr.model.service.knowledge.KnowledgeService;
import com.sp.sr.model.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhoulin
 */
@RestController
@RequestMapping("/api/example")
public class ExampleController extends BaseController {
    @Autowired
    ExampleService exampleService;

    @Autowired
    KnowledgeService knowledgeService;

    @RequestMapping("/list")
    @GetMapping
    public ResultVO<List<Example>> findAllByModuleCategoryAndModuleId(@RequestParam Long knowledgeId
    ) {
        return ResultVO.ok(exampleService.findAllByKnowledgeId(knowledgeId));
    }

    @GetMapping(path = "/findOne")
    public ResultVO<ExampleDTO> findOne(@RequestParam Long id) {
        ExampleDTO exampleDTO = exampleService.findOne(id);
        return ResultVO.ok(exampleDTO);
    }

    @PostMapping(path = "/delete")
    public ResultVO<Object> delete(@RequestParam Long id) {
        exampleService.delete(id);
        return ResultVO.ok();
    }
}
