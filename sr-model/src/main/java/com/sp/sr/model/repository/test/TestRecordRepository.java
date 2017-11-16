package com.sp.sr.model.repository.test;

import com.sp.sr.model.domain.test.TestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRecordRepository extends JpaRepository<TestRecord,Long>{
    Page<TestRecord> findAllByDeleteAtIsNullOrderByIdDesc(Pageable pageable);
    Page<TestRecord> findAllByStatusAndDeleteAtIsNullOrderByEndTimeDesc(Pageable pageable, Integer status);

}
