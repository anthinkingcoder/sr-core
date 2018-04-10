package com.sp.sr.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoulin
 */
@MappedSuperclass
@Data
public class BaseEntity implements Serializable{
    @Id
    @GeneratedValue
    protected Long id;
    protected Date createAt;
    @JsonIgnore
    protected Date updateAt;
    @JsonIgnore
    protected Date deleteAt;

    public BaseEntity() {
        createAt = new Date();
        updateAt = new Date();
    }
}
