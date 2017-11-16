package com.sp.sr.model.repository.question;

import com.sp.sr.model.domain.test.QuestionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhoulin
 */
public interface QuestionRecordRepository extends JpaRepository<QuestionRecord, Long> {

    List<QuestionRecord> findAllByStudentIdAndDeleteAtIsNull(Long studentId);

    QuestionRecord findByIdAndDeleteAtIsNull(Long id);

    List<QuestionRecord> findAllByQuestionCategoryIdInAndStudentId(List<Long> categoryIds,Long studentId);


}
