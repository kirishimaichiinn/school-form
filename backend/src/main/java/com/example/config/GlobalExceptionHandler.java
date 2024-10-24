package com.example.config;

import com.example.Util.FailureRestException;
import com.example.Util.RestBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<RestBean.RestData<Object>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return RestBean.failure(404, "没有此接口");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RestBean.RestData<Object>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return RestBean.failure(405, "方法不允许");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<RestBean.RestData<Object>> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return RestBean.failure("缺少参数");
    }

    @ExceptionHandler(FailureRestException.class)
    public ResponseEntity<RestBean.RestData<Object>> FailureRestException(FailureRestException ex){
        return RestBean.failure(ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestBean.RestData<Object>> handleGeneralException(Exception ex) {
        return RestBean.failure(500, "意外错误"+ex.getMessage());
    }
}
