package com.annotation.spzx.common.log.annotation;

import com.annotation.spzx.common.log.enums.OperatorType;
import org.checkerframework.checker.units.qual.A;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author
 * @Description:
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

     String title() ;								// 模块名称
     OperatorType operatorType() default OperatorType.MANAGE;	// 操作人类别
     int businessType() ;     // 业务类型（0其它 1新增 2修改 3删除）
     boolean isSaveRequestData() default true;   // 是否保存请求的参数
     boolean isSaveResponseData() default true;  // 是否保存响应的参数
}
