package com.sp.sr.model.enums;

/**
 * @author zhoulin
 */
public enum AnswerCategoryEnum {
    RADIO(1, "单选"),
    CHECKBOX(2, "多选");
    private Integer state;
    private String name;

    AnswerCategoryEnum(Integer state, String name) {
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
