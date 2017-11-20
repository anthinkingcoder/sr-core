package com.sp.sr.admin.question.controller;

import com.sp.sr.admin.common.Auths;
import com.sp.sr.admin.common.SrAdminException;
import com.sp.sr.model.domain.question.Question;
import com.sp.sr.model.domain.question.QuestionCategory;
import com.sp.sr.model.enums.AnswerCategoryEnum;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.service.question.QuestionCategoryService;
import com.sp.sr.model.service.question.QuestionService;
import com.sp.sr.model.util.Strings;
import com.sp.sr.model.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.Date;
import java.util.Objects;

import static com.sp.sr.model.controller.BaseController.USER;

@RestController
@RequestMapping("/api/admin/question")
@Slf4j
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionCategoryService categoryService;

    @GetMapping("/list")
    public ResultVO<Page<Question>> findAll(@RequestParam String title,
                                            @RequestParam Long categoryId,
                                            @RequestParam Integer size,
                                            @RequestParam Integer page) {
        if (StringUtils.hasText(title) && categoryId != null && categoryId != 0) {
            return ResultVO.ok(questionService.findAllByTitleContainsAndQuestionCategoryId(new PageRequest(page, size), title, categoryId));
        } else if (StringUtils.hasText(title)) {
            return ResultVO.ok(questionService.findAllByTitleContains(new PageRequest(page, size), title));
        } else if (categoryId != null && categoryId != 0) {
            return ResultVO.ok(questionService.findAllByQuestionCategoryId(new PageRequest(page, size), categoryId));
        } else {
            return ResultVO.ok(questionService.findAll(new PageRequest(page, size)));
        }
    }

    @PostMapping("/save")
    public ResultVO<Question> save(@RequestParam(required = false) Long id,
                                   @RequestParam String title,
                                   @RequestParam Long categoryId,
                                   @RequestParam(required = false) String possibleAnswerA,
                                   @RequestParam(required = false) String possibleAnswerB,
                                   @RequestParam(required = false) String possibleAnswerC,
                                   @RequestParam(required = false) String possibleAnswerD,
                                   @RequestParam(required = false) String possibleAnswerE,
                                   @RequestParam(required = false) String possibleAnswerF,
                                   @RequestParam Integer answerCategory,
                                   @RequestParam String answer,
                                   @RequestParam(required = false) String content,
                                   @RequestParam(required = false) String explain,
                                   @RequestParam(required = false,defaultValue = "1") Integer level
    ) {
        Question question;
        QuestionCategory questionCategory;
        if (categoryId != null && categoryId != 0) {
            questionCategory = categoryService.findOne(categoryId);
            if (questionCategory == null) {
                throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
            }
        }
        if (id != null && id != 0) {
            question = questionService.findById(id);
            if (question == null) {
                throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
            }
            Auths.verityUploader(USER.get(), question.getUploaderId());
        } else {
            question = new Question();
        }
        if (!Objects.equals(answerCategory, AnswerCategoryEnum.RADIO.getState()) && !Objects.equals(answerCategory, AnswerCategoryEnum.CHECKBOX.getState())) {
            throw new SrAdminException(ResultStatus.ARGUMENT_ERROR);
        }
        question.setTitle(title);
        question.setAnswer(Strings.orderAsc(answer,","));
        question.setContent(content);
        question.setUploaderId(USER.get().getId());
        question.setPossibleAnswerOne(possibleAnswerA);
        question.setPossibleAnswerTwo(possibleAnswerB);
        question.setPossibleAnswerThree(possibleAnswerC);
        question.setPossibleAnswerFour(possibleAnswerD);
        question.setPossibleAnswerFive(possibleAnswerE);
        question.setPossibleAnswerSix(possibleAnswerF);
        question.setLevel(level);
        question.setQuestionExplain(explain);
        question.setQuestionCategoryId(categoryId);
        question.setAnswerCategory(answerCategory);
        question = questionService.save(question);
        return ResultVO.ok(question);
    }

    @GetMapping("/findOne")
    public ResultVO<Question> findOne(Long id) {
        Question question = questionService.findById(id);
        if (question == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        return ResultVO.ok(question);
    }

    @PostMapping("/delete")
    public ResultVO<Question> delete(Long id) {
        Question question = questionService.findById(id);
        if (question == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        question.setDeleteAt(new Date());
        questionService.save(question);
        return ResultVO.ok(question);
    }
}
