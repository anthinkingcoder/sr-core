package com.sp.sr.model.repository.question;

import com.sp.sr.model.domain.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * @author zhoulin
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findByIdAndDeleteAtIsNull(Long id);

    List<Question> findAllByIdIn(List<Long> id);

    Page<Question> findAllByDeleteAtIsNullAndQuestionCategoryIdIn(List<Long> questionCategoryId,Pageable pageable);

    @Query(value = "select o.id from Question o where  o.level = :level and o.questionCategoryId in :ids")
    List<Long> findIdsByQuestionCategoryIdInAndLevel(@Param("ids") List<Long> questionCategoryIds, @Param("level") Integer level);

    @Query(value = "select count(o),o.questionCategoryId from Question  o group by o.questionCategoryId")
    List<Object[]> countAllGroupByQuestionCategoryId();

    Page<Question> findAllByDeleteAtIsNull(Pageable pageable);

    Page<Question> findAllByDeleteAtIsNullAndTitleContains(Pageable pageable, String title);

    Page<Question> findAllByDeleteAtIsNullAndTitleContainsAndQuestionCategoryId(Pageable pageable, String title, Long questionCategoryId);

    Page<Question> findAllByDeleteAtIsNullAndQuestionCategoryId(Pageable pageable, Long questionCategoryId);

}
