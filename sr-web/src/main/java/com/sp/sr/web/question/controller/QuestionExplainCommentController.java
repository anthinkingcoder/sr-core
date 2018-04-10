package com.sp.sr.web.question.controller;

import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.domain.question.explain.QuestionExplain;
import com.sp.sr.model.domain.question.explain.QuestionExplainAgree;
import com.sp.sr.model.domain.question.explain.QuestionExplainComment;
import com.sp.sr.model.domain.question.explain.QuestionExplainCommentAgree;
import com.sp.sr.model.domain.user.User;
import com.sp.sr.model.enums.AgreeStatusEnum;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.enums.RoleCategoryEnum;
import com.sp.sr.model.service.question.explain.QuestionExplainCommentAgreeService;
import com.sp.sr.model.service.question.explain.QuestionExplainCommentService;
import com.sp.sr.model.service.question.explain.QuestionExplainService;
import com.sp.sr.model.service.user.UserService;
import com.sp.sr.model.vo.QuestionExplainCommentVO;
import com.sp.sr.model.vo.ResultVO;
import com.sp.sr.web.SrWebException;
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
@RequestMapping("/api/question/explain/comment")
public class QuestionExplainCommentController {
    @Autowired
    private QuestionExplainCommentService questionExplainCommentService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionExplainService questionExplainService;
    @Autowired
    private QuestionExplainCommentAgreeService questionExplainCommentAgreeService;

    /**
     * 发表解析评论
     *
     * @param content   内容
     * @param explainId 解析id
     * @param toUserId  can null
     * @return resultVo
     */
    @PostMapping("/publish")
    public ResultVO<QuestionExplainCommentVO> publish(@RequestParam String content,
                                                      @RequestParam Long explainId,
                                                      @RequestParam(required = false) Long toUserId) {
        QuestionExplain questionExplain = questionExplainService.findById(explainId);
        if (questionExplain == null) {
            throw new SrWebException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        QuestionExplainComment questionExplainComment = new QuestionExplainComment();
        questionExplainComment.setContent(content);
        questionExplainComment.setQuestionExplainId(explainId);
        if (toUserId != null) {
            User toUser = userService.findUserById(toUserId);
            if (toUser == null || !Objects.equals(toUser.getLevel(), RoleCategoryEnum.STUDENT.getState())) {
                throw new SrWebException(ResultStatus.RESOURCE_NOT_FOUND);
            }
            questionExplainComment.setToUserId(toUserId);
            questionExplainComment.setToUserNickName(toUser.getName());
            questionExplainComment.setToUserHeadImg(toUser.getHeadImg());
        }
        questionExplainComment.setQuestionExplainId(explainId);
        questionExplainComment.setUserId(BaseController.USER.get().getId());
        questionExplainComment.setUserNickName(BaseController.USER.get().getName());
        questionExplainComment.setUserHeadImg(BaseController.USER.get().getHeadImg());
        questionExplainCommentService.save(questionExplainComment);
        QuestionExplainCommentVO questionExplainCommentVO = QuestionExplainCommentVO.builder()
                .username(questionExplainComment.getUserNickName())
                .userId(questionExplainComment.getUserId())
                .userHeadImg(questionExplainComment.getUserHeadImg())
                .toUserId(questionExplainComment.getToUserId())
                .toUsername(questionExplainComment.getToUserNickName())
                .toUserHeadImg(questionExplainComment.getToUserHeadImg())
                .agree(false)
                .agreeNum(questionExplainComment.getAgree())
                .editable(true)
                .commentId(questionExplainComment.getId())
                .publishTime(questionExplainComment.getCreateAt())
                .content(questionExplainComment.getContent()).build();
        return ResultVO.ok(questionExplainCommentVO);
    }

    @GetMapping("/list")
    public ResultVO<Page<QuestionExplainCommentVO>> list(@RequestParam Long explainId,
                                                         @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        QuestionExplain questionExplain = questionExplainService.findById(explainId);
        if (questionExplain == null) {
            throw new SrWebException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        Page<QuestionExplainComment> questionExplainCommentPage = questionExplainCommentService.findAllByQuestionExplainId(new PageRequest(page, size), explainId);
        List<QuestionExplainCommentVO> questionExplainCommentVOS = new ArrayList<>();
        QuestionExplainCommentVO questionExplainCommentVO;
        for (QuestionExplainComment questionExplainComment : questionExplainCommentPage.getContent()) {
            QuestionExplainCommentAgree questionExplainCommentAgree = questionExplainCommentAgreeService.findOne(BaseController.USER.get().getId(),questionExplainComment.getId());
            questionExplainCommentVO = QuestionExplainCommentVO.builder()
                    .username(questionExplainComment.getUserNickName())
                    .userId(questionExplainComment.getUserId())
                    .userHeadImg(questionExplainComment.getUserHeadImg())
                    .toUserId(questionExplainComment.getToUserId())
                    .toUsername(questionExplainComment.getToUserNickName())
                    .toUserHeadImg(questionExplainComment.getToUserHeadImg())
                    .agree(questionExplainCommentAgree != null && questionExplainCommentAgree.getAgree().equals(AgreeStatusEnum.LIKE.getState()))
                    .agreeNum(questionExplainComment.getAgree())
                    .editable(BaseController.USER.get().getId().equals(questionExplainComment.getUserId()))
                    .publishTime(questionExplainComment.getCreateAt())
                    .commentId(questionExplainComment.getId())
                    .content(questionExplainComment.getContent()).build();
            questionExplainCommentVOS.add(questionExplainCommentVO);
        }
        Page<QuestionExplainCommentVO> questionExplainCommentVOPage = new PageImpl<>(questionExplainCommentVOS, new PageRequest(page, size), questionExplainCommentPage.getTotalElements());
        return ResultVO.ok(questionExplainCommentVOPage);

    }

    @PostMapping("/delete")
    public ResultVO<Object> delete(@RequestParam Long commentId) {
        QuestionExplainComment questionExplainComment = questionExplainCommentService.findById(commentId);
        if (questionExplainComment == null) {
            throw new SrWebException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        if (!questionExplainComment.getUserId().equals(USER.get().getId())) {
            throw new SrWebException(ResultStatus.REPEAT_SAVE);
        }
        questionExplainComment.setDeleteAt(new Date());
        questionExplainCommentService.save(questionExplainComment);
        return ResultVO.ok();
    }

    @PostMapping("/agree")
    public ResultVO<Object> agree(@RequestParam Long commentId, @RequestParam Integer agree) {
        if (!Objects.equals(AgreeStatusEnum.DISLIE.getState(), agree) && !Objects.equals(AgreeStatusEnum.LIKE.getState(), agree)) {
            throw new SrWebException(ResultStatus.ARGUMENT_ERROR);
        }
        questionExplainCommentService.agree(commentId, agree);
        return ResultVO.ok();
    }

}
