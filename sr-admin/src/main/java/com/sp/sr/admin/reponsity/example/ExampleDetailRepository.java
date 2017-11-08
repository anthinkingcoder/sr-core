package com.sp.sr.admin.reponsity.example;

import com.sp.sr.model.domain.example.Example;
import com.sp.sr.model.domain.example.ExampleDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhoulin
 */
public interface ExampleDetailRepository extends JpaRepository<ExampleDetail, Long> {
    ExampleDetail findByIdAndDeleteAtIsNull(Long Id);
}
