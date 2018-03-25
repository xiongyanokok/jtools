package com.xy.jtools.exception;

import com.xy.jtools.enums.ErrorCodeEnum;

/**
 * 自定义异常
 * 
 * @author xiongyan
 * @date 2017年6月6日 下午4:19:08
 */
public class CustomException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 错误码
	 */
	private ErrorCodeEnum errorCode;
	
	public CustomException() {
		 super();
	}

    public CustomException(String message) {
        super(message);
    }
	
    public CustomException(ErrorCodeEnum errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public CustomException(String message, Throwable cause) {
    	super(message, cause);
    }
    
    public CustomException(ErrorCodeEnum errorCode, String message, Throwable cause) {
    	super(message, cause);
    	this.errorCode = errorCode;
    }
    
    public CustomException(Throwable cause) {
    	super(cause);
    }
    
	public ErrorCodeEnum getErrorCode() {
		return errorCode;
	}

}
