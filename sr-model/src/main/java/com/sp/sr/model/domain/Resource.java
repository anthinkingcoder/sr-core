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
@Entity(name = "core_resource")
@Data
public class Resource {
    @Id
    @GeneratedValue
    public Long id;
    public Date createAt;
    @JsonIgnore
    public Date updateAt;
    @JsonIgnore
    private Date deleteAt;
    private Integer category;
    private String coverUrl;
    private String url;
    private String name;
    private String summary;
    private Long uploaderId;

    public Resource() {
        createAt = new Date();
        updateAt = new Date();

    }
}
