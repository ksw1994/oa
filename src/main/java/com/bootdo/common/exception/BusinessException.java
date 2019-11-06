package com.bootdo.common.exception;

/**
 * 公共 业务异常类
 * @author lzy
 * @date 2018年4月25日
 */
public class BusinessException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String code;

	public BusinessException(String code,String message) {
		super(message);
		this.message = message;
		this.code = code;
	}

	public BusinessException() {
		super();
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
