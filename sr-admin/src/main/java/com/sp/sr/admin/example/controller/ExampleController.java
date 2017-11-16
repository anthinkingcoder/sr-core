package com.sp.sr.admin.example.controller;

import com.sp.sr.admin.common.SrAdminException;
import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.domain.knowledge.Knowledge;
import com.sp.sr.model.dto.ExampleDTO;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.service.example.ExampleService;
import com.sp.sr.model.service.KnowledgeService;
import com.sp.sr.model.vo.ResultVO;
import com.sp.sr.model.domain.example.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhoulin
 */
@RestController
@RequestMapping("/api/admin/example")
public class ExampleController extends BaseController {
    @Autowired
    ExampleService exampleService;

    @Autowired
    KnowledgeService knowledgeService;

    @RequestMapping("/list")
    @GetMapping
    public ResultVO<Page<Example>> findAllByModuleCategoryAndModuleId(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                      @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                      @RequestParam Long knowledgeId
    ) {
        return ResultVO.ok(exampleService.findAllByKnowledgeId(new PageRequest(page, size), knowledgeId));
    }

    @PostMapping(path = "/save")
    public ResultVO<ExampleDTO> save(
            @RequestParam(required = false) Long id,
            @RequestParam String content,
            @RequestParam String explain,
            @RequestParam String result,
            @RequestParam String title,
            @RequestParam(required = false) Long knowledgeId,
            @RequestParam Integer level
    ) {

        Knowledge knowledge = knowledgeService.findById(knowledgeId);
        if (knowledge == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        ExampleDTO exampleDTO = new ExampleDTO();
        exampleDTO.setId(id);
        exampleDTO.setExampleExplain(explain);
        exampleDTO.setOperatorId(USER.get().getId());
        exampleDTO.setKnowledgeId(knowledgeId);
        exampleDTO.setTopicId(knowledge.getTopicId());
        exampleDTO.setTitle(title);
        exampleDTO.setRuntimeResult(result);
        exampleDTO.setContent(content);
        exampleDTO.setLevel(level);
        exampleDTO.setUploaderId(USER.get().getId());
        exampleDTO = exampleService.save(USER.get(), exampleDTO);
        return ResultVO.ok(exampleDTO);

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
