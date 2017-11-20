package com.sp.sr.web.question.service;

import com.sp.sr.model.domain.question.explain.QuestionExplainAgree;
import com.sp.sr.model.repository.question.explain.QuestionExplainAgreeRepository;
import com.sp.sr.model.service.question.explain.QuestionExplainAgreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionExplainAgreeServiceImpl implements QuestionExplainAgreeService {
    @Autowired
    private QuestionExplainAgreeRepository questionExplainAgreeRepository;
    @Override
    public QuestionExplainAgree findOne(Long userId, Long explainId) {
        return questionExplainAgreeRepository.findByUserIdAndQuestionExplainId(userId,explainId);
    }
}
