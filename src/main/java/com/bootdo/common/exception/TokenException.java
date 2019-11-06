package com.bootdo.common.exception;



/**
 * Token异常类
 * 
 * @author lzy
 * @date 2018年6月1日
 */
public class TokenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TokenException(String message) {
        super(message);
    }
}
