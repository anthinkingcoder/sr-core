package com.sp.sr.web.question.service;

import com.sp.sr.model.domain.question.explain.QuestionExplainCommentAgree;
import com.sp.sr.model.repository.question.explain.QuestionExplainCommentAgreeRepository;
import com.sp.sr.model.service.question.explain.QuestionExplainCommentAgreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionExplainCommentAgreeServiceImpl implements QuestionExplainCommentAgreeService {
    @Autowired
    private QuestionExplainCommentAgreeRepository questionExplainCommentAgreeRepository;

    @Override
    public QuestionExplainCommentAgree findOne(Long userId, Long commentId) {
        return questionExplainCommentAgreeRepository.findByUserIdAndCommentId(userId, commentId);
    }
}
