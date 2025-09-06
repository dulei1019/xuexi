package com.atguigu.spzx.order.mapper;

import com.atguigu.spzx.model.entity.order.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author
 * @Description:
 */
@Mapper
public interface OrderItemMapper {
    /**
     * 保存订单详情
     * @param orderItem
     */
    void save(OrderItem orderItem);

    /**
     * 根据订单id查询订单详情
     * @param id
     * @return
     */
    List<OrderItem> findByOrderId(Long id);
}
