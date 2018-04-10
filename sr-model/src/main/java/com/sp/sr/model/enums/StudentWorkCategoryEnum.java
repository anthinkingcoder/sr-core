package com.sp.sr.model.enums;

/**
 * @author zhoulin
 */
public enum StudentWorkCategoryEnum {

    COURSE_DESIGN(1, "课程设计"),
    PRACTICAL_TRAINING(2, "项目实训"),
    COMPETITION_WORKS(3, "项目作品");
    private Integer state;
    private String name;

    StudentWorkCategoryEnum(Integer state, String name) {
        this.state = state;
        this.name = name;
    }

    public static boolean exists(Integer status) {
        return COURSE_DESIGN.getState().equals(status) || PRACTICAL_TRAINING.getState().equals(status) ||
                COMPETITION_WORKS.getState().equals(status);
    }

    public Integer getState() {
        return state;
    }

    public String getName() {
        return name;
    }
}
