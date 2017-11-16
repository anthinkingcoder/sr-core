package com.sp.sr.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class QuestionCategoryTreeVO {
    private QuestionCategoryVO questionCategoryVO;
    private List<QuestionCategoryVO> children;

    public QuestionCategoryTreeVO(QuestionCategoryVO questionCategoryVO, List<QuestionCategoryVO> children) {
        this.questionCategoryVO = questionCategoryVO;
        this.children = children;
    }

    public QuestionCategoryTreeVO() {
    }
}
