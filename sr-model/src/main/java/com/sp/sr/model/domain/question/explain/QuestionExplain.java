package com.sp.sr.model.domain.question.explain;

import com.sp.sr.model.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "core_question_explain")
@Data
public class QuestionExplain extends BaseEntity {
    private String content;
    private Long userid;
    private String userNickName;
    private String questionId;
    private Integer agree;
    private Integer commend;

}
