package com.sp.sr.model.domain.test;

import com.sp.sr.model.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author zhoulin
 */
@Entity(name = "core_student_test_record")
@Data
public class TestRecord extends BaseEntity{
    private String questionPath;
    private String answerPath;
    private String resultPath;
    private Integer score;
    private Integer status;
    private Integer level;
    private Integer origin;
    private Date startTime;
    private Date endTime;
    private Integer beyondPer;
    private Integer questionNum;
    private Integer questionRightNum;
    private Long studentId;
}
