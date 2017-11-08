package com.sp.sr.model.enums;

/**
 * @author zhoulin
 */
public enum ModuleCategoryEnum {

    DOCUMENT(1, "资源文档"),
    STUDENT_WORK(3, "学生作品"),
    EXAMPLE(2, "实例作品");
    private Integer state;
    private String name;

    ModuleCategoryEnum(Integer state, String name) {
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
