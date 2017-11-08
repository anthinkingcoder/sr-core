package com.sp.sr.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author zhoulin
 */
@Entity(name = "core_attachment")
@Data
public class Attachment {
    @Id
    @GeneratedValue
    public Long id;
    public Date createAt;
    @JsonIgnore
    public Date updateAt;
    @JsonIgnore
    private Date deleteAt;
    private String name;
    private Integer moduleCategory;
    private Long moduleId;
    private String url;
    private Integer sort;
    private Long uploaderId;

    public Attachment() {
        createAt = new Date();
        updateAt = new Date();
    }
}
