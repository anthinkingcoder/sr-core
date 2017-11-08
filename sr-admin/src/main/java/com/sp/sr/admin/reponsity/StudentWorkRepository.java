package com.sp.sr.admin.reponsity;

import com.sp.sr.model.domain.Resource;
import com.sp.sr.model.domain.ResourceDocument;
import com.sp.sr.model.domain.StudentWork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhoulin
 */
public interface StudentWorkRepository extends JpaRepository<StudentWork, Long> {
    Page<StudentWork> findAllByDeleteAtIsNullOrderByIdAsc(Pageable pageable);


    Page<StudentWork> findAllByDeleteAtIsNullAndUploaderIdOrderByIdAsc(Pageable pageable, Long uploaderId);


    StudentWork findByIdAndDeleteAtIsNull(Long id);


}
