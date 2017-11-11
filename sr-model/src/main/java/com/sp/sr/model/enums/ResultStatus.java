package com.sp.sr.model.enums;

/**
 * @author zhoulin
 */
public enum ResultStatus {
    SERVICE_ERROR(600, "服务器错误"),
    SUCCESS(666, "SUCCESS"),
    AUTHORIZE_ERROR(601, "认证错误"),
    ARGUMENT_ERROR(602, "请求参数未通过校验"),
    RESOURCE_NOT_FOUND(603, "资源不存在"),
    UPLOADER_ERROR(604, "非本人上传"),
    REPEAT_SAVE(605, "重复操作资源"),
    PERMISSION_ERROR(606, "没有权限"),
    RESOURCE_REPEAT(607,"资源重复增加");

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
