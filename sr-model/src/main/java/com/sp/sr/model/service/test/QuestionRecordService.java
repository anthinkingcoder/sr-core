package com.sp.sr.model.service.test;

import com.sp.sr.model.domain.test.QuestionRecord;

import java.util.List;

public interface QuestionRecordService {
    List<QuestionRecord> findAllByStudentId(Long studentId);

    QuestionRecord findById(Long id);

    QuestionRecord save(QuestionRecord questionRecord);
}
