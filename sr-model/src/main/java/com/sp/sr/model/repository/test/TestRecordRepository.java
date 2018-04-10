package com.sp.sr.model.repository.test;

import com.sp.sr.model.domain.test.TestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRecordRepository extends JpaRepository<TestRecord,Long>{
    Page<TestRecord> findAllByDeleteAtIsNullAndStudentIdOrderByIdDesc(Pageable pageable,Long studentId);
    Page<TestRecord> findAllByStatusAndDeleteAtIsNullAndStudentIdOrderByEndTimeDesc(Pageable pageable, Integer status,Long studentId);

}
