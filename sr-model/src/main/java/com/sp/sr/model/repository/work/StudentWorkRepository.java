package com.sp.sr.model.repository.work;

import com.sp.sr.model.domain.work.StudentWork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhoulin
 */
public interface StudentWorkRepository extends JpaRepository<StudentWork, Long> {
    Page<StudentWork> findAllByDeleteAtIsNullOrderByIdAsc(Pageable pageable);

    Page<StudentWork> findAllByDeleteAtIsNullAndCategoryOrderByIdAsc(Pageable pageable, Integer category);

    Page<StudentWork> findAllByDeleteAtIsNullAndTitleContains(Pageable pageable, String title);

    Page<StudentWork> findAllByDeleteAtIsNullAndUploaderIdOrderByIdAsc(Pageable pageable, Long uploaderId);

    StudentWork findByIdAndDeleteAtIsNull(Long id);

}
