package com.sp.sr.model.domain.knowledge;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.sr.model.domain.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "core_resource_document")
@Data
public class ResourceDocument extends BaseEntity {
    private String coverUrl;
    private String content;
    private Long uploaderId;

}
