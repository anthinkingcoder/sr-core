package com.sp.sr.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "core_expand_knowledge")
@Data
public class ExpandKnowledge extends BaseEntity{

    private String name;
    private String summary;
    private String coverUrl;
    private String url;
    private Long uploaderId;

}
