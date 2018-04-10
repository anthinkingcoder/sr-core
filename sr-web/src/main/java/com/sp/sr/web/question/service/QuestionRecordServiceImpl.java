package com.sp.sr.web.question.service;
import com.sp.sr.model.domain.test.QuestionRecord;
import com.sp.sr.model.repository.question.QuestionRecordRepository;
import com.sp.sr.model.service.test.QuestionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author zhoulin
 */
@Service
public class QuestionRecordServiceImpl implements QuestionRecordService {

    @Autowired
    private QuestionRecordRepository questionRecordRepository;

    @Override
    public List<QuestionRecord> findAllByStudentId(Long studentId) {
        return questionRecordRepository.findAllByStudentIdAndDeleteAtIsNull(studentId);
    }

    @Override
    public QuestionRecord findById(Long id) {
        return questionRecordRepository.findByIdAndDeleteAtIsNull(id);
    }

    @Override
    public QuestionRecord save(QuestionRecord questionRecord) {
        return questionRecordRepository.save(questionRecord);
    }
}
