package com.sp.sr.model.domain.test;

import com.sp.sr.model.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

/**
 * @author zhoulin
 */
@Entity(name = "core_question_record")
@Data
public class QuestionRecord extends BaseEntity {
    private Long questionCategoryId;
    private String path;
    private String wrongNumPath;
    private Integer questionNum;
    private Long studentId;
    private String resultPath;
}
