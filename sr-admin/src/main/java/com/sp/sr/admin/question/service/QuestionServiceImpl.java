package com.sp.sr.admin.question.service;

import com.sp.sr.model.domain.question.Question;
import com.sp.sr.model.repository.question.QuestionCategoryRepository;
import com.sp.sr.model.repository.question.QuestionRepository;
import com.sp.sr.model.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoulin
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository repository;

    @Autowired
    private QuestionCategoryRepository questionCategoryRepository;

    @Override
    public Question findById(Long id) {
        return repository.findByIdAndDeleteAtIsNull(id);
    }

    @Override
    public List<Question> findAllByIdIn(List<Long> id) {
        return repository.findAllByIdIn(id);
    }

    @Override
    public Page<Question> findAll(Pageable pageable) {
        return repository.findAllByDeleteAtIsNull(pageable);
    }

    @Override
    public Page<Question> findAllByTitleContains(Pageable pageable, String title) {
        return repository.findAllByDeleteAtIsNullAndTitleContains(pageable, title);
    }

    @Override
    public Page<Question> findAllByTitleContainsAndQuestionCategoryId(Pageable pageable, String title, Long questionCategoryId) {
        return repository.findAllByDeleteAtIsNullAndTitleContainsAndQuestionCategoryId(pageable, title, questionCategoryId);
    }

    @Override
    public Page<Question> findAllByQuestionCategoryId(Pageable pageable, Long questionCategoryId) {
        return repository.findAllByDeleteAtIsNullAndQuestionCategoryId(pageable, questionCategoryId);
    }

    @Override
    public Question save(Question question) {
        return repository.save(question);
    }

}
