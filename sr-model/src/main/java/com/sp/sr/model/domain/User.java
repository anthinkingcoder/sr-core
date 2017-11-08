package com.sp.sr.model.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoulin
 */
@Entity(name = "core_user")
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @JsonIgnore
    private Date createAt;
    @JsonIgnore
    private Date updateAt;
    @JsonIgnore
    private Date deleteAt;
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String salt;
    private String name;
    private Date lastLoginTime;
    private Integer level;

    @JsonIgnore
    private Integer status;

    public User() {
        createAt = new Date();
        updateAt = new Date();
    }
}
