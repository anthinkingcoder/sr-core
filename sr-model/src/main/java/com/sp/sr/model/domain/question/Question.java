package com.sp.sr.model.domain.question;
import com.sp.sr.model.domain.BaseEntity;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author zhoulin
 */
@Entity
@Table(name = "core_question")
@Data
public class Question extends BaseEntity {
    private String title;
    private Integer level;
    private String possibleAnswerOne;
    private String possibleAnswerTwo;
    private String possibleAnswerThree;
    private String possibleAnswerFour;
    private String possibleAnswerFive;
    private String possibleAnswerSix;
    private Integer answerCategory;
    private Long questionCategoryId;
    private Long uploaderId;
    private String answer;
    private String questionExplain;
    private Long topicId;
    private String content;
}
