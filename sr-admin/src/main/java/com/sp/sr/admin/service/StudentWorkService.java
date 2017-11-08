package com.sp.sr.admin.service;

import com.sp.sr.model.domain.StudentWork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author zhoulin
 */
public interface StudentWorkService {
    StudentWork save(StudentWork studentWork);

    Page<StudentWork> findAll(Pageable pageable);

    StudentWork findOne(Long id);
}
