package com.sp.sr.admin.question.controller;
import com.sp.sr.admin.common.SrAdminException;
import com.sp.sr.model.domain.question.QuestionCategory;
import com.sp.sr.model.dto.QuestionCategoryTreeDTO;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.service.question.QuestionCategoryService;
import com.sp.sr.model.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

/**
 * @author zhoulin
 */
@RestController
@RequestMapping("/api/admin/question/category")
public class QuestionCategoryController {
    @Autowired
    private QuestionCategoryService questionCategoryService;

    @GetMapping("/tree")
    public ResultVO<List<QuestionCategoryTreeDTO>> findAllQuestionCategoryTree() {
        return ResultVO.ok(questionCategoryService.findTree());
    }

    @GetMapping("/list")
    public ResultVO<Page<QuestionCategory>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResultVO.ok(questionCategoryService.findAll(new PageRequest(page, size)));
    }

    @GetMapping("/all")
    public ResultVO<List<QuestionCategory>> findAllByDepth(@RequestParam Integer depth) {
        return ResultVO.ok(questionCategoryService.findAllByDepth(depth));
    }

    @PostMapping("/save")
    public ResultVO<QuestionCategory> save(@RequestParam String name,
                                           @RequestParam(required = false) String summary,
                                           @RequestParam(required = false) Long parentId,
                                           @RequestParam(required = false) Long id,
                                           @RequestParam(required = false) Integer sort) {
        try{
            QuestionCategory questionCategory;
            if (id != null && id != 0) {
                questionCategory = questionCategoryService.findOne(id);
                if (questionCategory == null) {
                    throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
                }
            } else {
                questionCategory = new QuestionCategory();
                questionCategory.setDepth(0);
            }
            QuestionCategory parentQuestionCategory;
            if (parentId != null && parentId != 0) {
                parentQuestionCategory = questionCategoryService.findOne(parentId);
                if (parentQuestionCategory == null) {
                    throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
                }

                if (questionCategory.getId() == null) {
                    if (parentQuestionCategory.getDepth() != 0) {
                        throw new SrAdminException(ResultStatus.ARGUMENT_ERROR);
                    }
                }else {
                    if (questionCategory.getDepth() < parentQuestionCategory.getDepth() || Objects.equals(questionCategory.getId(), parentQuestionCategory.getId())) {
                        throw new SrAdminException(ResultStatus.ARGUMENT_ERROR);
                    }
                }


                if (!Objects.equals(questionCategory.getParentId(), parentQuestionCategory.getId())) {
                    String path;
                    if (!StringUtils.hasText(parentQuestionCategory.getPath())) {
                        path = parentQuestionCategory.getId() + "";
                    } else {
                        path = parentQuestionCategory.getPath() + "/" + parentQuestionCategory.getId();
                    }
                    parentQuestionCategory.setPath(path);
                }
                questionCategory.setParentId(parentQuestionCategory.getId());
                questionCategory.setDepth(parentQuestionCategory.getDepth() + 1);
            }
            questionCategory.setName(name);
            questionCategory.setSort(sort);
            questionCategory.setSummary(summary);
            return ResultVO.ok(questionCategoryService.save(questionCategory));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/findOne")
    public ResultVO<QuestionCategory> findOne(@RequestParam Long id) {
        return ResultVO.ok(questionCategoryService.findOne(id));
    }

}
