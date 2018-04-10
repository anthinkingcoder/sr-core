package com.sp.sr.model.enums;

/**
 * @author zhoulin
 * 测试题目数量枚举常量
 */
public enum TestQuestionNumEnum {
    FIVE(5, "5"),
    TEN(10, "10"),
    TWENTY(20, "20"),
    THIRTY(30, "30");
    private Integer state;
    private String name;

    TestQuestionNumEnum(Integer state, String name) {
        this.state = state;
        this.name = name;
    }

    public static boolean exists(Integer status) {
        return FIVE.getState().equals(status) || TEN.getState().equals(status) ||
                TWENTY.getState().equals(status) || THIRTY.getState().equals(status);
    }

    public Integer getState() {
        return state;
    }

    public String getName() {
        return name;
    }
}
