package com.sp.sr.model.dto;

import com.sp.sr.model.domain.question.Question;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TestRecordDTO {
    private Long testRecordId;
    private String[] categoryIds;
    private Integer level;
    private Integer questionNum;
    private Integer questionRightNum;
    private Integer origin;
    private String answerPath;
    private String resultPath;
    private Date startTime;
    private Date endTime;
    private Integer score;
    private Integer status;
    private Long studentId;
    private QuestionDTO questionTestRecord;
    private List<Long> questionIds;

    public TestRecordDTO(String[] categoryIds, Integer level, Integer questionNum, Integer origin) {
        this.categoryIds = categoryIds;
        this.level = level;
        this.questionNum = questionNum;
        this.origin = origin;
    }

    public TestRecordDTO(Long testRecordId, Integer level, Integer questionNum, Integer questionRightNum, Integer origin, String answerPath, String resultPath, Date startTime, Integer score, Integer status, Long studentId) {
        this.testRecordId = testRecordId;
        this.level = level;
        this.questionNum = questionNum;
        this.questionRightNum = questionRightNum;
        this.origin = origin;
        this.answerPath = answerPath;
        this.resultPath = resultPath;
        this.startTime = startTime;
        this.score = score;
        this.status = status;
        this.studentId = studentId;
    }
}
