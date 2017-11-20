package com.sp.sr.web.question.service;

import com.sp.sr.model.domain.question.Question;
import com.sp.sr.model.repository.question.QuestionRepository;
import com.sp.sr.model.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public Question findById(Long id) {
        return questionRepository.findByIdAndDeleteAtIsNull(id);
    }

    @Override
    public List<Question> findAllByIdIn(List<Long> id) {
        return null;
    }

    @Override
    public Page<Question> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Question> findAllByTitleContains(Pageable pageable, String title) {
        return null;
    }

    @Override
    public Page<Question> findAllByTitleContainsAndQuestionCategoryId(Pageable pageable, String title, Long questionCategoryId) {
        return null;
    }

    @Override
    public Page<Question> findAllByQuestionCategoryId(Pageable pageable, Long questionCategoryId) {
        return null;
    }

    @Override
    public Question save(Question question) {
        return null;
    }
}
