package com.sp.sr.model.enums;

/**
 * @author zhoulin
 */
public enum ResultStatus {
    SERVICE_ERROR(600, "服务器错误"),
    SUCCESS(666, "SUCCESS"),
    AUTHORIZE_ERROR(601,"认证错误");
    private Integer code;
    private String message;

    ResultStatus(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
