package com.atguigu.spzx.common.exception;


import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author
 * @Description:
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理
    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.build(e, ResultCodeEnum.SYSTEM_ERROR);
    }

    //自定义异常处理`
    @ExceptionHandler(GuiguException.class)
    public Result error(GuiguException e) {
        return Result.build(null, e.getResultCodeEnum());
    }




}
