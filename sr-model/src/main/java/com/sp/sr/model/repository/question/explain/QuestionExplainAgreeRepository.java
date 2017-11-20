package com.sp.sr.model.repository.question.explain;
import com.sp.sr.model.domain.question.explain.QuestionExplainAgree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionExplainAgreeRepository extends JpaRepository<QuestionExplainAgree,Long> {
    QuestionExplainAgree findByUserIdAndQuestionExplainId(Long userId, Long questionExplainId);

}
