package com.sp.sr.model.service.test;

import com.sp.sr.model.domain.test.TestRecord;
import com.sp.sr.model.dto.QuestionDTO;
import com.sp.sr.model.dto.TestRecordDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TestRecordService {
    TestRecordDTO create(TestRecordDTO testRecordDTO);

    TestRecordDTO findOne(Long testRecordId);

    QuestionDTO findOneQuestionTestRecord(Long questionId, Long testRecordId);

    TestRecord updateAnswer(String answer, Long testRecordId, Long questionId);

    TestRecord evaluate(Long testRecordId);

    Page<TestRecord> findAll(Pageable pageable);

    Page<TestRecord> findAllByStatus(Pageable pageable,Integer status);


}
