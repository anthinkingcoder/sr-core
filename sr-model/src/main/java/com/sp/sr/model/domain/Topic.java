package com.sp.sr.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "core_topic")
@Data
public class Topic {
    @Id
    @GeneratedValue
    private Long id;
    private Date createAt;
    @JsonIgnore
    private Date updateAt;
    @JsonIgnore
    private Date deleteAt;
    private Long uploaderId;
    private String name;

    public Topic() {
        createAt = new Date();
        updateAt = new Date();
    }
}
