package com.sp.sr.model.service.question.explain;

import com.sp.sr.model.domain.question.explain.QuestionExplain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionExplainService {
    QuestionExplain save(QuestionExplain questionExplain);

    Page<QuestionExplain> findAllByQuestionId(Pageable pageable, Long questionId, Integer editExplain);

    QuestionExplain findById(Long id);

    QuestionExplain findOne(Long userId, Long questionId);


    /**
     * 设为精选
     *
     * @param questionExplainId 题目解析id
     */
    QuestionExplain commend(Long questionExplainId, Integer commend);


    /**
     * 点赞题目解析
     *
     * @param questionExplainId 题目解析id
     */
    void agree(Long questionExplainId, Integer agree);


}
