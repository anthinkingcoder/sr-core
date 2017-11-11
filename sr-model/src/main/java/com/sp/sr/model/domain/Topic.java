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
public class Topic extends BaseEntity{

    private Long uploaderId;
    private String name;
    private String coverUrl;
    private Long categoryId;

}
