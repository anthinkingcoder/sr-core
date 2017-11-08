package com.sp.sr.admin.controller;

import com.sp.sr.admin.service.ExampleService;
import com.sp.sr.model.dto.ExampleDTO;
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
    @RequestMapping("/list")
    @GetMapping
    public ResultVO<Page<Example>> findAllByModuleCategoryAndModuleId(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                      @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        return ResultVO.ok(exampleService.findAll(new PageRequest(page, size)));
    }

    @PostMapping(path = "/save")
    public ResultVO<ExampleDTO> save(
            @RequestParam(required = false) Long id,
            @RequestParam String content,
            @RequestParam String explain,
            @RequestParam String result,
            @RequestParam String title,
            @RequestParam (required = false) Long knowledgeId,
            @RequestParam(required = false) Long topicId
    ) {
        ExampleDTO exampleDTO = new ExampleDTO();
        exampleDTO.setId(id);
        exampleDTO.setExampleExplain(explain);
        exampleDTO.setOperatorId(USER.get().getId());
        exampleDTO.setKnowledgeId(knowledgeId);
        exampleDTO.setTopicId(topicId);
        exampleDTO.setTitle(title);
        exampleDTO.setRuntimeResult(result);
        exampleDTO.setContent(content);
        exampleDTO.setUploaderId(USER.get().getId());
        exampleDTO = exampleService.save(USER.get(),exampleDTO);
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
