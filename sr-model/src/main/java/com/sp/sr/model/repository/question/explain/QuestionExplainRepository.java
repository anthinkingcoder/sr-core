package com.sp.sr.model.repository.question.explain;

import com.sp.sr.model.domain.question.explain.QuestionExplain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface QuestionExplainRepository extends JpaRepository<QuestionExplain, Long> {
    Page<QuestionExplain> findAllByDeleteAtIsNullAndQuestionIdAndCommendOrderByIdAsc(Pageable pageable, Long questionId,Integer commend);

    QuestionExplain findByIdAndDeleteAtIsNull(Long id);

    QuestionExplain findByQuestionIdAndUserIdAndDeleteAtIsNull(Long questionId, Long userId);

    QuestionExplain findByCommendAndQuestionIdAndDeleteAtIsNull(Integer commend, Long questionId);

    @Modifying
    @Transactional
    @Query("update QuestionExplain o set o.agree = o.agree + 1 where o.id = :eId")
    int increaseAgree(@Param("eId") Long questionExplainId);
    @Modifying
    @Transactional
    @Query("update QuestionExplain o set o.agree = o.agree - 1 where o.id = :eId and o.agree > 0")
    int decreaseAgree(@Param("eId") Long questionExplainId);
    @Modifying
    @Transactional
    @Query("update QuestionExplain o set o.comment = o.comment + 1 where o.id = :eId")
    int increaseComment(@Param("eId") Long questionExplainId);
    @Modifying
    @Transactional
    @Query("update QuestionExplain o set o.comment = o.comment - 1 where o.id = :eId and o.comment > 0")
    int decreaseComment(@Param("eId") Long questionExplainId);


}
