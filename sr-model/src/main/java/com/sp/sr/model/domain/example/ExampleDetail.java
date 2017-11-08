package com.sp.sr.model.domain.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ExampleDetail {
    @Id
    @GeneratedValue
    public Long id;
    public Date createAt;
    @JsonIgnore
    public Date updateAt;
    @JsonIgnore
    private Date deleteAt;
    private String content;
    private String exampleExplain;
    private String runtimeResult;

    public ExampleDetail() {
        createAt = new Date();
        updateAt = new Date();
    }
}
