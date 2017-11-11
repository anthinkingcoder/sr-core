package com.sp.sr.admin.service.impl;

import com.sp.sr.admin.Auths;
import com.sp.sr.model.repository.StudentWorkRepository;
import com.sp.sr.model.service.StudentWorkService;
import com.sp.sr.model.domain.StudentWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.sp.sr.model.controller.BaseController.USER;

/**
 * @author zhoulin
 */
@Service
public class StudentWorkServiceImpl implements StudentWorkService {
    @Autowired
    private StudentWorkRepository repository;

    @Override
    public StudentWork save(StudentWork studentWork) {
        return repository.save(studentWork);
    }

    @Override
    public Page<StudentWork> findAll(Pageable pageable) {
        if (Auths.isSystem(USER.get())) {
            return repository.findAllByDeleteAtIsNullOrderByIdAsc(pageable);
        }
        return repository.findAllByDeleteAtIsNullAndUploaderIdOrderByIdAsc(pageable,USER.get().getId());
    }

    @Override
    public StudentWork findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Page<StudentWork> findAllByCategory(Pageable pageable, Integer category) {
        return null;
    }
}
