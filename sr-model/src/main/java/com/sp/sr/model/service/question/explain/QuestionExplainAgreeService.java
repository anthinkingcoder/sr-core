package com.sp.sr.model.service.question.explain;

import com.sp.sr.model.domain.question.explain.QuestionExplainAgree;

public interface QuestionExplainAgreeService {
    QuestionExplainAgree findOne(Long userId, Long explainId);
}
