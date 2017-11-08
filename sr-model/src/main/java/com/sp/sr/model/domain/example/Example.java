package com.sp.sr.model.domain.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "core_example")
@Data
public class Example {
    @Id
    @GeneratedValue
    public Long id;
    public Date createAt;
    @JsonIgnore
    public Date updateAt;
    @JsonIgnore
    private Date deleteAt;
    private String title;
    private Long exampleDetailId;
    private Long knowledgeId;
    private Long topicId;
    private Long uploaderId;

    public Example() {
        createAt = new Date();
        updateAt = new Date();
    }
}
