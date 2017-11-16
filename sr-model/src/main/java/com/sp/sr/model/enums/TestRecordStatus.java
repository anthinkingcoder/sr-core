package com.sp.sr.model.enums;

public enum TestRecordStatus {
    FINISH(1, "'已完成'"),
    UN_FINISH(0, "未完成");


    private Integer code;
    private String message;

    TestRecordStatus(Integer code, String message) {
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
