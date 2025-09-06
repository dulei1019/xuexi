package com.atguigu.spzx.common.exception;

import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * 自定义异常处理类
 */

@Data
public class GuiguException extends RuntimeException{
    private Integer code;
    private String mssage;
    private ResultCodeEnum resultCodeEnum;


    public GuiguException(ResultCodeEnum resultCodeEnum) {
       this.resultCodeEnum = resultCodeEnum;
       this.code = resultCodeEnum.getCode();
       this.mssage = resultCodeEnum.getMessage();
    }


}
