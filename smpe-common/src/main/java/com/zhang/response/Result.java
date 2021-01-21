package com.zhang.response;

import com.zhang.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * ClassName Result
 * Description TODO 类描述：对返回前端数据进行封装
 *
 * @author ZhangRenjie
 * Date  2020/12/24 9:39
 */
@Data
@AllArgsConstructor
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    /**
     * Description //TODO 成功，只返回状态码和响应信息，没有返回数据
     * @return Result<E> 封装返回的状态码和响应信息
     * @author ZhangRenJie
     * @date 2021/1/20 8:24
     */
    public static <E> Result<E> success(){
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), null);
    }

    /**
     * Description //TODO 成功并封装返回数据
     * @param data 返回数据
     * @return Result<E> 封装返回的数据
     * @author ZhangRenJie
     * @date 2021/1/20 8:24
     */
    public static <E> Result<E> success(E data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), data);
    }
}