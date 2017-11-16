package com.sp.sr.model.domain.question.explain;

import com.sp.sr.model.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "core_question_explain_comment_agree")
@Data
public class QuestionExplainAgree extends BaseEntity {
    private Long questionExplainId;
    private Long userId;
}
