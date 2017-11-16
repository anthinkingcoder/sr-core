package com.sp.sr.model.domain.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.sr.model.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "core_example")
@Data
public class Example extends BaseEntity {
    private String title;
    private Long exampleDetailId;
    private Long knowledgeId;
    private Long topicId;
    private Long uploaderId;
    private Integer level;

}
