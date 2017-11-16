package com.sp.sr.admin.question.service;

import com.sp.sr.model.domain.question.QuestionCategory;
import com.sp.sr.model.dto.QuestionCategoryTreeDTO;
import com.sp.sr.model.repository.question.QuestionCategoryRepository;
import com.sp.sr.model.service.QuestionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoulin
 */
@Service
public class QuestionCategoryServiceImpl implements QuestionCategoryService {

    @Autowired
    private QuestionCategoryRepository repository;

    @Override
    public List<QuestionCategory> findAllByParentId(Long parentId) {
        return repository.findAllByDeleteAtIsNullAndParentId(parentId);
    }

    @Override
    public List<QuestionCategory> findAllByDepth(Integer depth) {
        return repository.findAllByDeleteAtIsNullAndDepth(depth);
    }

    @Override
    public Page<QuestionCategory> findAll(Pageable pageable) {
        return repository.findAllByDeleteAtIsNull(pageable);
    }

    @Override
    public QuestionCategory save(QuestionCategory questionCategory) {
        return repository.save(questionCategory);
    }

    @Override
    public List<QuestionCategoryTreeDTO> findTree() {
        return null;
    }

    @Override
    public QuestionCategory findOne(Long id) {
        return repository.findByIdAndDeleteAtIsNull(id);
    }
}
