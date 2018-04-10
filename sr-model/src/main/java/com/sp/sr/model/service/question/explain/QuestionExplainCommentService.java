package com.sp.sr.model.service.question.explain;

import com.sp.sr.model.domain.question.explain.QuestionExplainComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionExplainCommentService {
    QuestionExplainComment save(QuestionExplainComment questionExplainComment);

    Page<QuestionExplainComment> findAllByQuestionExplainId(Pageable pageable, Long questionExplainId);

    void agree(Long commentId, Integer agree);

    QuestionExplainComment findById(Long commentId);


}
