package com.sp.sr.model.vo;
import lombok.Builder;
import lombok.Data;
import java.util.Date;
/**
 * @author zhoulin
 */
@Data
@Builder
public class QuestionExplainCommentVO {
    private Long userId;
    private String username;
    private Long toUserId;
    private String userHeadImg;
    private String toUserHeadImg;
    private String toUsername;
    private String content;
    private Integer agreeNum;
    private Boolean agree;
    private Boolean editable;
    private Date publishTime;
    private Long commentId;

}
