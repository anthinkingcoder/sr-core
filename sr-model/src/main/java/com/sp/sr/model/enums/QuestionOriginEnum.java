package com.sp.sr.model.enums;

public enum QuestionOriginEnum {
    NEW(1, "新题"),
    WRONG(2, "错题"),
    NEW_AND_WRONG(3, "新题+错题"),
    OTHER(4, "不限来源");
    private Integer state;
    private String name;

    QuestionOriginEnum(Integer state, String name) {
        this.state = state;
        this.name = name;
    }

    public static boolean exists(Integer status) {
        return NEW.getState().equals(status) || WRONG.getState().equals(status) ||
                NEW_AND_WRONG.getState().equals(status) || OTHER.getState().equals(status);
    }

    public Integer getState() {
        return state;
    }

    public String getName() {
        return name;
    }
}
