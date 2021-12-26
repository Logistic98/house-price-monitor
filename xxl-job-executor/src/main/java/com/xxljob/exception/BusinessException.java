package com.xxljob.exception;

import com.xxljob.enums.BaseCodeEnum;

public class BusinessException extends RuntimeException {

    private int code;

    private String message;

    public BusinessException(String message){
        super(message);
        this.message = message;
    }

    public BusinessException(int code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(BaseCodeEnum baseCodeEnum){
        this(baseCodeEnum.getCode(),baseCodeEnum.getMessage());
    }

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }
}
