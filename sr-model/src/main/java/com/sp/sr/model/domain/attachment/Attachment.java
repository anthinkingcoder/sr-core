package com.sp.sr.model.domain.attachment;

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
@Entity(name = "core_attachment")
@Data
public class Attachment extends BaseEntity {
    private String name;
    private Integer moduleCategory;
    private Long moduleId;
    private String url;
    private Integer sort;
    private Long uploaderId;

}
