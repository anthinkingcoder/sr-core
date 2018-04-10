package com.sp.sr.model.domain.question.explain;

import com.sp.sr.model.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "core_question_explain_comment")
@Data
public class QuestionExplainComment extends BaseEntity {
    private String content;
    private Long questionExplainId;
    private Long userId;
    private String userNickName;
    private String toUserHeadImg;
    private String userHeadImg;
    private String toUserNickName;
    private Long toUserId;
    private Integer agree = 0;
}
