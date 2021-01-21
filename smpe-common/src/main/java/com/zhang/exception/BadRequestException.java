package com.zhang.exception;

import com.zhang.enums.ResultEnum;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * ClassName BadRequestException
 * Description TODO 类描述：通用请求异常
 *
 * @author ZhangRenjie
 * Date  2020/12/24 11:15
 */
public class BadRequestException extends RuntimeException {
    private Integer status = BAD_REQUEST.value();
    public BadRequestException(String msg) {
        super(msg);
    }
    public BadRequestException(Integer status, String msg) {
        super(msg);
        this.status = status;
    }
    public BadRequestException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.status = resultEnum.getCode();
    }
}