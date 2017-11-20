package com.sp.sr.web.work.service;

import com.sp.sr.model.domain.work.StudentWork;
import com.sp.sr.model.repository.work.StudentWorkRepository;
import com.sp.sr.model.service.work.StudentWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author zhoulin
 */
@Service
public class StudentWorkServiceImpl implements StudentWorkService {
    @Autowired
    private StudentWorkRepository repository;

    @Override
    public StudentWork save(StudentWork studentWork) {
        return null;
    }

    @Override
    public Page<StudentWork> findAll(Pageable pageable) {
        return repository.findAllByDeleteAtIsNullOrderByIdAsc(pageable);
    }

    @Override
    public StudentWork findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Page<StudentWork> findAllByCategory(Pageable pageable, Integer category) {
        return repository.findAllByDeleteAtIsNullAndCategoryOrderByIdAsc(pageable, category);
    }

    @Override
    public Page<StudentWork> findAllByTitle(String title, Pageable pageable) {
        return repository.findAllByDeleteAtIsNullAndTitleContains(pageable, title);
    }
}
