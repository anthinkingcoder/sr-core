package com.sp.sr.admin.controller;

import com.sp.sr.admin.Auths;
import com.sp.sr.admin.SrAdminException;
import com.sp.sr.model.domain.Knowledge;
import com.sp.sr.model.domain.ResourceDocument;
import com.sp.sr.model.domain.TopicCategory;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.service.TopicCategoryService;
import com.sp.sr.model.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/admin/topic/category")
public class TopicCategoryController {
    @Autowired
    private TopicCategoryService topicCategoryService;


    @GetMapping(path = "/list")
    public ResultVO<List<TopicCategory>> findAll() {
        return ResultVO.ok(topicCategoryService.findAll());
    }

    @PostMapping("/save")
    public ResultVO<TopicCategory> save(@RequestParam(required = false) Long id,
                                        @RequestParam String name,
                                        @RequestParam(required = false) String summary,
                                        @RequestParam Integer sort) {
        TopicCategory topicCategory;
        if (id != null && id != 0) {
            topicCategory = topicCategoryService.findOne(id);
            if (topicCategory == null) {
                throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
            }
        } else {
            topicCategory = new TopicCategory();
        }
        topicCategory.setName(name);
        topicCategory.setSort(sort);
        topicCategory.setSummary(summary);

        topicCategory = topicCategoryService.save(topicCategory);
        return ResultVO.ok(topicCategory);
    }

    @GetMapping("/findOne")
    public ResultVO<TopicCategory> findOne(@RequestParam Long id) {
        TopicCategory topicCategory = topicCategoryService.findOne(id);
        return ResultVO.ok(topicCategory);
    }


    @PostMapping(path = "/delete")
    public ResultVO<Object> delete(@RequestParam Long id) {
        TopicCategory topicCategory = topicCategoryService.findOne(id);
        if (topicCategory == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        topicCategory.setDeleteAt(new Date());
        topicCategoryService.save(topicCategory);
        return ResultVO.ok(topicCategory);
    }

}
