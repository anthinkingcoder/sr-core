package com.sp.sr.model.domain;

import lombok.Data;

import javax.persistence.Entity;

/**
 * @author zhoulin
 */
@Entity(name = "core_topic_category")
@Data
public class TopicCategory extends BaseEntity {
    private String name;
    private String summary;
    private Integer sort;

}
