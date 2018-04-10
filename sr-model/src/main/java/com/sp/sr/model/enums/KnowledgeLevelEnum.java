package com.sp.sr.model.enums;

public enum KnowledgeLevelEnum {
    BASE(1, "基础知识"),
    TOPIC(2, "进阶知识");
    private Integer state;
    private String name;

    KnowledgeLevelEnum(Integer state, String name) {
        this.state = state;
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public String getName() {
        return name;
    }
}
