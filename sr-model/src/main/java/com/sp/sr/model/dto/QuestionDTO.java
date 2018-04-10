package com.sp.sr.model.dto;

import com.sp.sr.model.domain.question.Question;
import lombok.Data;

/**
 * @author zhoulin
 */
@Data
public class QuestionDTO {
    private Question question;
    private Boolean right;
    private Boolean done;
    private String answer;
}
