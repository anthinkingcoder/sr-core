package com.sp.sr.model.dto;
import lombok.Data;
import java.util.Date;

@Data
public class ExampleDTO {
    public Long id;
    public Date createAt;
    private String title;
    private Long exampleDetailId;
    private String content;
    private String exampleExplain;
    private String runtimeResult;
    private Long knowledgeId;
    private Long topicId;
    private Long uploaderId;
    private Long operatorId;
    private Integer level;


}
