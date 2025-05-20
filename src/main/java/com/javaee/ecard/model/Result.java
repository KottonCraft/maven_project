package com.javaee.ecard.model;

import com.javaee.ecard.config.ErrorCodeProperties;
import com.javaee.ecard.util.SpringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
@Data
public class Result {
    private int code;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(int code, Object data) {
        this.code = code;
        ErrorCodeProperties errorCodeProperties = SpringUtils.getBean(ErrorCodeProperties.class);
        this.message = errorCodeProperties.getCode().get(String.valueOf(code));
        this.data = data;
    }

    public Result(int code, Object data, String message) {
        this.code = code;
        ErrorCodeProperties errorCodeProperties = SpringUtils.getBean(ErrorCodeProperties.class);
        this.message = errorCodeProperties.getCode().get(String.valueOf(code));
        this.message = this.message+"  "+message;
        this.data = data;
    }
    public static Result success(Object data) {
        return new Result(0, data);
    }

    public static Result failed(int errorCode) {
        return new Result(errorCode, null);
    }
    public static Result failed(int errorCode, String message) {
        return new Result(errorCode, null, message);
    }

}
