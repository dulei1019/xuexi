package com.atguigu.spzx.order.mapper;

import com.atguigu.spzx.model.entity.order.OrderLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author
 * @Description:
 */
@Mapper
public interface OrderLogMapper {
    /**
     * 保存订单日志
     * @param orderLog
     */
    void save(OrderLog orderLog);
}
