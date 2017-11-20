package com.sp.sr.model.service.work;

import com.sp.sr.model.domain.topic.Topic;
import com.sp.sr.model.domain.work.StudentWork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author zhoulin
 */
public interface StudentWorkService {
    StudentWork save(StudentWork studentWork);

    Page<StudentWork> findAll(Pageable pageable);

    StudentWork findOne(Long id);

    Page<StudentWork> findAllByCategory(Pageable pageable, Integer category);

    Page<StudentWork> findAllByTitle(String title, Pageable pageable);
}
