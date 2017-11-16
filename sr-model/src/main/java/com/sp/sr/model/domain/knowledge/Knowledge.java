package com.sp.sr.model.domain.knowledge;

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
@Entity(name = "core_knowledge")
@Data
public class Knowledge extends BaseEntity {
    private String name;
    private Integer sort;
    private Long parentId;
    private Integer level;
    private Long uploaderId;
    private Long topicId;
    private Long resourceDocumentId;


}
