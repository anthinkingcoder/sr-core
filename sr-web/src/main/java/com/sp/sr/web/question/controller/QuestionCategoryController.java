package com.sp.sr.web.question.controller;

import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.domain.test.QuestionRecord;
import com.sp.sr.model.domain.question.QuestionCategory;
import com.sp.sr.model.dto.QuestionCategoryTreeDTO;
import com.sp.sr.model.service.QuestionCategoryService;
import com.sp.sr.model.service.QuestionRecordService;
import com.sp.sr.model.vo.QuestionCategoryTreeVO;
import com.sp.sr.model.vo.QuestionCategoryVO;
import com.sp.sr.model.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zhoulin
 */
@RestController
@RequestMapping("/api/question/category")
public class QuestionCategoryController {
    @Autowired
    private QuestionCategoryService questionCategoryService;

    @Autowired
    private QuestionRecordService questionRecordService;


    @GetMapping("/tree")
    public ResultVO<List<QuestionCategoryTreeVO>> findAllQuestionCategoryTree() {
        //questionCategoryTreeDTOS -> QuestionCategoryTreeVO
        List<QuestionCategoryTreeDTO> questionCategoryTreeDTOS = questionCategoryService.findTree();
        List<QuestionRecord> questionRecords = questionRecordService.findAllByStudentId(BaseController.USER.get().getId());
        List<QuestionCategoryTreeVO> questionCategoryTreeVOS = new ArrayList<>();
        for (QuestionCategoryTreeDTO questionCategoryTreeDTO : questionCategoryTreeDTOS) {
            QuestionCategoryTreeVO questionCategoryTreeVO = new QuestionCategoryTreeVO();
            QuestionCategoryVO questionCategoryVO = new QuestionCategoryVO();
            questionCategoryVO.setQuestionCategoryId(questionCategoryTreeDTO.getQuestion().getId());
            questionCategoryVO.setQuestionCategoryName(questionCategoryTreeDTO.getQuestion().getName());
            questionCategoryTreeVO.setQuestionCategoryVO(questionCategoryVO);
            if (questionCategoryTreeDTO.getChildren() != null) {
                List<QuestionCategoryVO> questionCategoryVOS = new ArrayList<>();
                QuestionCategoryVO questionCategoryVOChild;
                for (QuestionCategoryTreeDTO child : questionCategoryTreeDTO.getChildren()) {
                    questionCategoryVOChild = new QuestionCategoryVO();
                    questionCategoryVOChild.setQuestionCategoryId(child.getQuestion().getId());
                    questionCategoryVOChild.setQuestionCategoryName(child.getQuestion().getName());

                    questionCategoryVOChild.setQuestionNum(child.getQuestionNum());

                    questionCategoryVOChild.setAlreadyDoneQuestionNum(0);
                    for (QuestionRecord questionRecord : questionRecords) {
                        if (Objects.equals(questionRecord.getId(), child.getQuestion().getId())) {
                            questionCategoryVOChild.setAlreadyDoneQuestionNum(questionRecord.getPath() == null ? 0 : questionRecord.getPath().split(",").length);
                            break;
                        }
                    }
                    questionCategoryVOS.add(questionCategoryVOChild);
                }
                questionCategoryTreeVO.setChildren(questionCategoryVOS);
            }
            questionCategoryTreeVOS.add(questionCategoryTreeVO);
        }
        return ResultVO.ok(questionCategoryTreeVOS);
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

    @GetMapping("/findOne")
    public ResultVO<QuestionCategory> findOne(@RequestParam Long id) {
        return ResultVO.ok(questionCategoryService.findOne(id));
    }

}
