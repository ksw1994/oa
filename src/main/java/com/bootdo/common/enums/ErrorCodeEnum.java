package com.bootdo.common.enums;

/**
 * @program: intern
 * @description: 系统错误编码枚举类
 * @author: Conten
 * @create: 2018-12-15 21:27
 **/
public enum ErrorCodeEnum {
    SUCCESS(200,"请求成功"),
    SYSTEM_ERROR(800,"系统错误"),
    PARAM_ERROR(801,"参数错误"),
    ;
    private Integer code;

    private String msg;

    ErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
