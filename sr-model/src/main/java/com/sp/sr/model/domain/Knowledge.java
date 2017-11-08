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
public class Knowledge{

    @Id
    @GeneratedValue
    public Long id;
    public Date createAt;
    @JsonIgnore
    public Date updateAt;
    @JsonIgnore
    private Date deleteAt;
    private String name;
    private Integer sort;
    private Long parentId;
    private Integer level;
    private Long uploaderId;
    private Long topicId;
    private Long resourceDocumentId;

    public Knowledge() {
        createAt = new Date();
        updateAt = new Date();
    }
}
