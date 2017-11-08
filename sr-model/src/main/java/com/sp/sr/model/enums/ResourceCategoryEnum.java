package com.sp.sr.model.enums;


/**
 * @author zhoulin
 */
public enum ResourceCategoryEnum {
    BOOK(1, "课程设计"),
    SOFTWARE(2, "软件资源"),
    WEBSITE(3, "优秀网站");
    private Integer state;
    private String name;

    ResourceCategoryEnum(Integer state, String name) {
        this.state = state;
        this.name = name;
    }

    public static boolean exists(Integer status) {
        return BOOK.getState().equals(status) || SOFTWARE.getState().equals(status) ||
                WEBSITE.getState().equals(status);
    }

    public Integer getState() {
        return state;
    }

    public String getName() {
        return name;
    }
}
