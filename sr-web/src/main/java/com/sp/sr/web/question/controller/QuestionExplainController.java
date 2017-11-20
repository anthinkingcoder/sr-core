package com.sp.sr.web.question.controller;

import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.domain.question.Question;
import com.sp.sr.model.domain.question.explain.QuestionExplain;
import com.sp.sr.model.domain.question.explain.QuestionExplainAgree;
import com.sp.sr.model.enums.AgreeStatusEnum;
import com.sp.sr.model.enums.QuestionExplainCommendEnum;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.repository.question.explain.QuestionExplainAgreeRepository;
import com.sp.sr.model.service.question.QuestionService;
import com.sp.sr.model.service.question.explain.QuestionExplainAgreeService;
import com.sp.sr.model.service.question.explain.QuestionExplainService;
import com.sp.sr.model.vo.QuestionExplainVO;
import com.sp.sr.model.vo.ResultVO;
import com.sp.sr.web.SrWebException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.sp.sr.model.controller.BaseController.USER;

@RestController
@RequestMapping("/api/question/explain")
@Slf4j
public class QuestionExplainController {
    @Autowired
    private  QuestionExplainService questionExplainService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionExplainAgreeService questionExplainAgreeService;


    /**
     * 发表解析
     *
     * @param content    内容
     * @param questionId 问题编号
     * @param explainId  问题解析编号 为空 则为新增
     * @return ResultVO
     */
    @RequestMapping("/publish")
    public ResultVO<QuestionExplainVO> publish(@RequestParam String content,
                                               @RequestParam Long questionId,
                                               @RequestParam(required = false) Long explainId) {

        //验证问题合法性
        Question question = questionService.findById(questionId);
        if (question == null) {
            throw new SrWebException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        //验证问题解析合法性
        QuestionExplain questionExplain;
        if (explainId != null && explainId != 0) {
            questionExplain = questionExplainService.findById(explainId);
            if (questionExplain == null) {
                throw new SrWebException(ResultStatus.RESOURCE_NOT_FOUND);
            }
            if (!questionExplain.getUserId().equals(USER.get().getId())) {
                throw new SrWebException(ResultStatus.PERMISSION_ERROR);
            }
        } else {
            questionExplain = questionExplainService.findOne(USER.get().getId(), questionId);
            if (questionExplain != null) {
                throw new SrWebException(ResultStatus.REPEAT_SAVE);
            }
            questionExplain = new QuestionExplain();
            questionExplain.setUserId(USER.get().getId());
            questionExplain.setQuestionId(questionId);
            questionExplain.setUserNickName(USER.get().getName());
            questionExplain.setUserHeadImg(USER.get().getHeadImg());
        }
        questionExplain.setContent(content);
        questionExplain = questionExplainService.save(questionExplain);
        QuestionExplainVO questionExplainVO = QuestionExplainVO.builder()
                .content(questionExplain.getContent())
                .agree(false)
                .agreeNum(questionExplain.getAgree())
                .comment(questionExplain.getComment())
                .questionExplainId(questionExplain.getId())
                .userHeadImg(questionExplain.getUserHeadImg())
                .commend(false)
                .editable(true)
                .publishTime(questionExplain.getCreateAt())
                .userId(questionExplain.getUserId())
                .username(questionExplain.getUserNickName())
                .build();

        return ResultVO.ok(questionExplainVO);
    }


    @PostMapping("/delete")
    public ResultVO<Object> delete(@RequestParam Long explainId) {
        QuestionExplain questionExplain = questionExplainService.findById(explainId);
        if (questionExplain == null) {
            throw new SrWebException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        if (!questionExplain.getUserId().equals(USER.get().getId())) {
            throw new SrWebException(ResultStatus.REPEAT_SAVE);
        }
        questionExplain.setDeleteAt(new Date());
        questionExplainService.save(questionExplain);
        return ResultVO.ok();
    }

    @GetMapping("/canPublish")
    public ResultVO<Boolean> canPublish(
            @RequestParam Long questionId
    ) {
        QuestionExplain questionExplain = questionExplainService.findOne(USER.get().getId(), questionId);
        if (questionExplain == null) {
            return ResultVO.ok(true);
        }
        return ResultVO.ok(false);
    }

    @PostMapping("/agree")
    public ResultVO<Object> agree(@RequestParam Long explainId, @RequestParam Integer agree) {
        if (!Objects.equals(AgreeStatusEnum.DISLIE.getState(), agree) && !Objects.equals(AgreeStatusEnum.LIKE.getState(), agree)) {
            throw new SrWebException(ResultStatus.ARGUMENT_ERROR);
        }
        questionExplainService.agree(explainId, agree);
        return ResultVO.ok();
    }

    @GetMapping("/list")
    public ResultVO<Page<QuestionExplainVO>> list(@RequestParam Long questionId,
                                                  @RequestParam(required = false) Integer editExplain,
                                                  @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "10") Integer size) {
        //验证问题合法性
        Question question = questionService.findById(questionId);
        if (question == null) {
            throw new SrWebException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        Page<QuestionExplain> questionExplainPage = questionExplainService.findAllByQuestionId(new PageRequest(page, size), questionId, editExplain);
        //拼接vo
        List<QuestionExplainVO> questionExplainVOList = new ArrayList<>(questionExplainPage.getContent().size());
        QuestionExplainVO questionExplainVO;
        for (QuestionExplain questionExplain : questionExplainPage.getContent()) {
            QuestionExplainAgree questionExplainAgree = questionExplainAgreeService.findOne(BaseController.USER.get().getId(), questionExplain.getId());
            questionExplainVO = QuestionExplainVO.builder()
                    .content(questionExplain.getContent())
                    .agree(questionExplainAgree != null && questionExplainAgree.getAgree().equals(AgreeStatusEnum.LIKE.getState()))
                    .agreeNum(questionExplain.getAgree())
                    .comment(questionExplain.getComment())
                    .questionExplainId(questionExplain.getId())
                    .userHeadImg(questionExplain.getUserHeadImg())
                    .commend(questionExplain.getCommend().equals(QuestionExplainCommendEnum.COMMEND.getState()))
                    .editable(USER.get().getId().equals(questionExplain.getUserId()))
                    .publishTime(questionExplain.getCreateAt())
                    .userId(questionExplain.getUserId())
                    .username(questionExplain.getUserNickName())
                    .showComment(false)
                    .build();
            questionExplainVOList.add(questionExplainVO);
        }
        Page<QuestionExplainVO> questionExplainVOPage = new PageImpl<>(questionExplainVOList, new PageRequest(page, size), questionExplainPage.getTotalElements());
        return ResultVO.ok(questionExplainVOPage);
    }
}
