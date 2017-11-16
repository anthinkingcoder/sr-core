package com.sp.sr.model.vo;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
/**
 * @author zhoulin
 */
@Setter
@Getter
public class QuestionCategoryVO {

    private Long questionCategoryId;
    private String questionCategoryName;
    /**
     * 该类别下的题目数量
     */
    private Long questionNum;
    /**
     * 已做题目数量
     */
    private Integer alreadyDoneQuestionNum;

    public QuestionCategoryVO(Long questionCategoryId, String questionCategoryName, Long questionNum, Integer alreadyDoneQuestionNum) {
        this.questionCategoryId = questionCategoryId;
        this.questionCategoryName = questionCategoryName;
        this.questionNum = questionNum;
        this.alreadyDoneQuestionNum = alreadyDoneQuestionNum;
    }

    public QuestionCategoryVO() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof QuestionCategoryVO) {
            QuestionCategoryVO v = (QuestionCategoryVO) obj;
            return Objects.equals(this.questionCategoryId, v.getQuestionCategoryId());
        } else {
            return false;
        }

    }
}
