package com.house.exception;

import com.house.enums.ResponseCode;
import com.house.utils.Response;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;


/**
 * 全局异常
 */
@RestControllerAdvice
public class HandlerException {

    private static Logger logger = LoggerFactory.getLogger(HandlerException.class);

    /**
     * 捕获 MethodArgumentNotValidException
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<String> handleBusinessException(MethodArgumentNotValidException e){
        logger.error("【参数异常】，{}",e);
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        return Response.error(fieldError.getDefaultMessage());
    }

    /**
     * 捕获 BusinessException（shiro授权）
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public Response<String> handleBusinessException(UnauthorizedException e){
        logger.error("【权限异常】，{}",e);
        return Response.error(ResponseCode.NOT_PERMISSION.getCode(),ResponseCode.NOT_PERMISSION.getMessage());
    }

    /**
     * 捕获 BusinessException
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public Response<String> handleBusinessException(BusinessException e){
        logger.error("【业务异常】，{}",e);
        return Response.error(e.getCode(),e.getMessage());
    }

    /**
     * 捕获 Exception
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Response<String> handleException(Exception e){
        if (e instanceof MaxUploadSizeExceededException){
            logger.error("【上传文件异常】，{}",e);
            return Response.error("文件最大不能超过1M");
        }
        logger.error("【系统异常】，{}",e);
        return Response.error("系统异常");
    }
}
