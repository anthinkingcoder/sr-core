package com.sp.sr.web.question.service;

import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.domain.question.explain.QuestionExplainComment;
import com.sp.sr.model.domain.question.explain.QuestionExplainCommentAgree;
import com.sp.sr.model.enums.AgreeStatusEnum;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.repository.question.explain.QuestionExplainCommentAgreeRepository;
import com.sp.sr.model.repository.question.explain.QuestionExplainCommentRepository;
import com.sp.sr.model.repository.question.explain.QuestionExplainRepository;
import com.sp.sr.model.service.question.explain.QuestionExplainCommentService;
import com.sp.sr.web.SrWebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionExplainCommentServiceImpl implements QuestionExplainCommentService {
    @Autowired
    private QuestionExplainCommentRepository questionExplainCommentRepository;

    @Autowired
    private QuestionExplainCommentAgreeRepository agreeRepository;

    @Autowired
    private QuestionExplainRepository questionExplainRepository;

    @Override
    public QuestionExplainComment save(QuestionExplainComment questionExplainComment) {
        if (questionExplainComment.getId() == null) {
            questionExplainRepository.increaseComment(questionExplainComment.getQuestionExplainId());
        } else if (questionExplainComment.getDeleteAt() != null) {
            questionExplainRepository.decreaseComment(questionExplainComment.getQuestionExplainId());
        }
        return questionExplainCommentRepository.save(questionExplainComment);
    }

    @Override
    public Page<QuestionExplainComment> findAllByQuestionExplainId(Pageable pageable, Long questionId) {
        return questionExplainCommentRepository.findAllByDeleteAtIsNullAndQuestionExplainIdOrderById(pageable, questionId);
    }


    @Override
    public void agree(Long commentId, Integer agree) {
        QuestionExplainComment questionExplainComment = questionExplainCommentRepository.findByIdAndDeleteAtIsNull(commentId);
        if (questionExplainComment == null) {
            throw new SrWebException(ResultStatus.RESOURCE_NOT_FOUND);
        }

        QuestionExplainCommentAgree questionExplainCommentAgree = agreeRepository.findByUserIdAndCommentId(BaseController.USER.get().getId(), commentId);
        if (questionExplainCommentAgree == null) {
            questionExplainCommentAgree = new QuestionExplainCommentAgree();
            questionExplainCommentAgree.setAgree(AgreeStatusEnum.LIKE.getState());
            questionExplainCommentAgree.setUserId(BaseController.USER.get().getId());
            questionExplainCommentAgree.setCommentId(commentId);
        } else {
            if (questionExplainCommentAgree.getAgree().equals(agree)) {
                return;
            }
            questionExplainCommentAgree.setAgree(agree);
        }
        questionExplainCommentAgree = agreeRepository.save(questionExplainCommentAgree);
        if (questionExplainCommentAgree.getAgree().equals(AgreeStatusEnum.DISLIE.getState())) {
            questionExplainCommentRepository.decreaseAgree(commentId);
        } else {
            questionExplainCommentRepository.increaseAgree(commentId);
        }
    }

    @Override
    public QuestionExplainComment findById(Long commentId) {
        return questionExplainCommentRepository.findByIdAndDeleteAtIsNull(commentId);
    }


}
