package com.example.htx.utils;

public enum ResultEnum {

    success(1666, "成功"),
    fail(1667, "失败"),
    RESULT_CODE_PARAM_KEY_MISS(1035, "缺少必要参数"),
    true_name(1000, "姓名可以使用"),
    false_name(1001, "姓名重复"),
    true_tel(1002, "电话可以使用"),
    false_tel(1003, "电话重复");
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
