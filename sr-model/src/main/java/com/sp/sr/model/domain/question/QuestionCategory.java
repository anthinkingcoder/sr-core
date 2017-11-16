package com.sp.sr.model.domain.question;

import com.sp.sr.model.domain.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * @author zhoulin
 */
@Entity
@Table(name = "core_question_category")
@Data
public class QuestionCategory extends BaseEntity {
    private String name;
    private String summary;
    private String path;
    private Integer depth;
    private Long parentId;
    private Integer sort;

}
