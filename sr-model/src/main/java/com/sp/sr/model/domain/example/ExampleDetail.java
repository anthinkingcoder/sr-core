package com.sp.sr.model.domain.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.sr.model.domain.BaseEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author zhoulin
 */
@Entity(name = "core_example_detail")
@Data
public class ExampleDetail extends BaseEntity{
    private String content;
    private String exampleExplain;
    private String runtimeResult;

}
