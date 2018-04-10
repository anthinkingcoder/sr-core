package com.sp.sr.model.service.question;

import com.sp.sr.model.domain.question.Question;
import com.sp.sr.model.dto.QuestionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhoulin
 */
public interface QuestionService {
    Question findById(Long id);

    List<Question> findAllByIdIn(List<Long> id);

    Page<Question> findAll(Pageable pageable);

    Page<Question> findAllByTitleContains(Pageable pageable, String title);

    Page<Question> findAllByTitleContainsAndQuestionCategoryId(Pageable pageable, String title, Long questionCategoryId);

    Page<Question> findAllByQuestionCategoryId(Pageable pageable, Long questionCategoryId);

    Question save(Question question);






}
