package com.sp.sr.model.repository.question;

import com.sp.sr.model.domain.question.QuestionCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author zhoulin
 */
public interface QuestionCategoryRepository extends JpaRepository<QuestionCategory, Long> {
    Page<QuestionCategory> findAllByDeleteAtIsNull(Pageable pageable);

    QuestionCategory findByIdAndDeleteAtIsNull(Long id);

    List<QuestionCategory> findAllByDeleteAtIsNullAndDepth(Integer depth);

    List<QuestionCategory> findAllByDeleteAtIsNullAndParentId(Long parentId);







}
