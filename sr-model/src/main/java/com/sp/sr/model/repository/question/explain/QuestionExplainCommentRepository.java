package com.sp.sr.model.repository.question.explain;
import com.sp.sr.model.domain.question.explain.QuestionExplainComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface QuestionExplainCommentRepository extends JpaRepository<QuestionExplainComment, Long> {
    Page<QuestionExplainComment> findAllByDeleteAtIsNullAndQuestionExplainIdOrderById(Pageable pageable, Long questionExplainId);

    QuestionExplainComment findByIdAndDeleteAtIsNull(Long id);
    @Transactional
    @Modifying
    @Query("update QuestionExplainComment o set o.agree = o.agree + 1 where o.id = :cId")
    int increaseAgree(@Param("cId") Long commentId);
    @Transactional
    @Modifying
    @Query("update QuestionExplainComment o set o.agree = o.agree - 1 where o.id = :cId and o.agree > 0")
    int decreaseAgree(@Param("cId") Long commentId);

}
