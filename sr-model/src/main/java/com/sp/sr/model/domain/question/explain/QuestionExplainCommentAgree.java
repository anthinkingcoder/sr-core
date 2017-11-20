package com.sp.sr.model.domain.question.explain;

import com.sp.sr.model.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "core_question_explain_comment_agree")
@Data
public class QuestionExplainCommentAgree extends BaseEntity {
    private Long commentId;
    private Long userId;
    private Integer agree;
}
