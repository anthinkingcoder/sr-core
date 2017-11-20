package com.sp.sr.model.service.question.explain;

import com.sp.sr.model.domain.question.explain.QuestionExplainCommentAgree;

public interface QuestionExplainCommentAgreeService {
    QuestionExplainCommentAgree findOne(Long userId, Long commentId);
}
