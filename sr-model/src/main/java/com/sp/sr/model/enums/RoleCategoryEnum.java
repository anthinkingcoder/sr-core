package com.sp.sr.model.enums;

/**
 * @author zhoulin
 */
public enum RoleCategoryEnum {
    SYSTEM(1, "超级管理员"),
    TEACHER(2, "教师"),
    STUDENT(3, "学生");

    private Integer state;
    private String name;

    RoleCategoryEnum(Integer state, String name) {
        this.state = state;
        this.name = name;
    }

    public static boolean exists(Integer status) {
        return SYSTEM.getState().equals(status) || TEACHER.getState().equals(status) ||
                STUDENT.getState().equals(status);
    }

    public Integer getState() {
        return state;
    }

    public String getName() {
        return name;
    }

}
