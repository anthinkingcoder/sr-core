package com.sp.sr.model.enums;

public enum  QuestionExplainCommendEnum {
    COMMEND(1, "精选"),
    NORMAL(0, "非精选");
    private Integer state;
    private String name;

    QuestionExplainCommendEnum(Integer state, String name) {
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
