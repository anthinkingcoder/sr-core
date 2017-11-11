package com.sp.sr.model.repository;

import com.sp.sr.model.domain.StudentWork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhoulin
 */
public interface StudentWorkRepository extends JpaRepository<StudentWork, Long> {
    Page<StudentWork> findAllByDeleteAtIsNullOrderByIdAsc(Pageable pageable);

    Page<StudentWork> findAllByDeleteAtIsNullAndCategoryOrderByIdAsc(Pageable pageable,Integer category);


    Page<StudentWork> findAllByDeleteAtIsNullAndUploaderIdOrderByIdAsc(Pageable pageable, Long uploaderId);


    StudentWork findByIdAndDeleteAtIsNull(Long id);




}
