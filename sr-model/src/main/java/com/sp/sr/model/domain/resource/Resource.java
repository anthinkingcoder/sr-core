package com.sp.sr.model.domain.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.sr.model.domain.BaseEntity;
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
public class Resource extends BaseEntity {
    private Integer category;
    private String coverUrl;
    private String url;
    private String name;
    private String summary;
    private Long uploaderId;

}
