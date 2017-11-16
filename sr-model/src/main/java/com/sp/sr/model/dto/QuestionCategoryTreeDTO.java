package com.sp.sr.model.dto;

import com.sp.sr.model.domain.question.QuestionCategory;
import lombok.Data;

import java.util.List;

/**
 * @author zhoulin
 * 问题类别树DTO 暂时只做二层级类别树
 */
@Data
public class QuestionCategoryTreeDTO {
    private QuestionCategory question;
    private Long questionNum;
    private List<QuestionCategoryTreeDTO> children;
}
