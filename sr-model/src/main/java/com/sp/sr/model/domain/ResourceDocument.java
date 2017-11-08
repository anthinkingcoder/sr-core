package com.sp.sr.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "core_resource_document")
@Data
public class ResourceDocument {
    @Id
    @GeneratedValue
    private Long id;
    private Date createAt;
    @JsonIgnore
    private Date updateAt;
    @JsonIgnore
    private Date deleteAt;
    private String coverUrl;
    private String content;
    private Long uploaderId;

    public ResourceDocument() {
        createAt = new Date();
        updateAt = new Date();
    }
}
