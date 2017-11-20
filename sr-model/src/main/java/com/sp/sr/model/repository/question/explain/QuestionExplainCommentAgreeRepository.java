package com.sp.sr.model.repository.question.explain;

import com.sp.sr.model.domain.question.explain.QuestionExplainAgree;
import com.sp.sr.model.domain.question.explain.QuestionExplainComment;
import com.sp.sr.model.domain.question.explain.QuestionExplainCommentAgree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionExplainCommentAgreeRepository extends JpaRepository<QuestionExplainCommentAgree, Long> {
    QuestionExplainCommentAgree findByUserIdAndCommentId(Long userId, Long commentId);
}
