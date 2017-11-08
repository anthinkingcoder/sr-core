package com.sp.sr.admin.reponsity.example;

import com.sp.sr.model.domain.example.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhoulin
 */
public interface ExampleRepository extends JpaRepository<Example, Long> {
    Example findByIdAndDeleteAtIsNull(Long id);

    Page<Example> findAllByDeleteAtIsNullOrderByCreateAtAsc(Pageable pageable);
    Page<Example> findAllByDeleteAtIsNullAndUploaderIdOrderByCreateAtAsc(Pageable pageable,Long uploaderId);


}
