package com.sp.sr.model.repository.example;

import com.sp.sr.model.domain.example.ExampleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhoulin
 */
public interface ExampleDetailRepository extends JpaRepository<ExampleDetail, Long> {
    ExampleDetail findByIdAndDeleteAtIsNull(Long Id);
}
