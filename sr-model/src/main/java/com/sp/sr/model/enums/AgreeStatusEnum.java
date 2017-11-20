package com.sp.sr.model.enums;

public enum  AgreeStatusEnum {
    LIKE(1, "赞同"),
    DISLIE(0, "不赞同");
    private Integer state;
    private String name;

    AgreeStatusEnum(Integer state, String name) {
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
