package com.sp.sr.model.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class QuestionExplainVO {
    private String content;
    private Long questionExplainId;
    private Integer agreeNum;
    private Boolean agree;
    private Integer comment;
    private Boolean editable;
    private String username;
    private Long userId;
    private String userHeadImg;
    private Date publishTime;
    private Boolean commend;
    private Boolean showComment;
}
