package com.sp.sr.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "core_student_work")
@Data
public class StudentWork extends BaseEntity{
    private String title;
    private String author;
    private String summary;
    private String content;
    private Integer category;
    private Long uploaderId;

}
