package com.sp.sr.model.domain.question.explain;

import com.sp.sr.model.domain.BaseEntity;
import com.sp.sr.model.enums.QuestionExplainCommendEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "core_question_explain")
@Data
public class QuestionExplain extends BaseEntity {
    private String content;
    private Long userId;
    private String userNickName;
    private Long questionId;
    private String userHeadImg;
    private Integer agree = 0;
    private Integer commend = QuestionExplainCommendEnum.NORMAL.getState();
    private Integer comment = 0;

}
