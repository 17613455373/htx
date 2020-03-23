package com.example.htx.respon;

/**
 * @Description: 调用状态枚举
 * @Author: muchunfa
 * @CreateDate: 2018/3/1 15:10
 */
public enum ResultCodeEnum {
    /**
     * 操作成功
     */
    RESULT_CODE_SUCCESS(200, "操作成功"),
    RESULT_CODE_ERROR(500, "操作失败"),
    RESULT_CODE_NULL(400, "无数据");


    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;

    ResultCodeEnum(Integer code, String message) {
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
