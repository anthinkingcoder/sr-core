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
@Entity(name = "core_knowledge")
@Data
public class Knowledge {
    @Id
    @GeneratedValue
    private Long id;
    private Date createAt;
    @JsonIgnore
    private Date updateAt;
    @JsonIgnore
    private Date deleteAt;
    private String name;
    private Integer sort;
    private Long parentId;
    private String path;
    private Integer level;
    private Long uploaderId;
    private Long topicId;
    private Long resourceDocumentId;

    public Knowledge() {
        createAt = new Date();
        updateAt = new Date();
    }
}
