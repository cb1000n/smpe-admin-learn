package com.zhang.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ClassName ResultEnum
 * Description TODO 类描述：统一显影消息枚举
 *
 * @author ZhangRenjie
 * Date  2020/12/24 9:45
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(0, "SUCCESS")
    ;

    private int code;

    private String msg;
}