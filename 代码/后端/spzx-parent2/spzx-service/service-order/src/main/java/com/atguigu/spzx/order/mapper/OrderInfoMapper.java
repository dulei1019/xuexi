package com.atguigu.spzx.order.mapper;

import com.atguigu.spzx.model.entity.order.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author
 * @Description:
 */
@Mapper
public interface OrderInfoMapper {
    /**
     * 保存订单信息
     * @param orderInfo
     */
    void save(OrderInfo orderInfo);

    /**
     * 根据订单id查询订单信息
     * @param orderId
     * @return
     */
    OrderInfo getById(Long orderId);

    /**
     * 根据用户id和订单状态查询订单信息
     * @param userId
     * @param orderStatus
     * @return
     */
    List<OrderInfo> findUserPage(Long userId, Integer orderStatus);

    /**
     * 根据订单号查询订单信息
     * @param orderNo
     * @return
     */
    OrderInfo getByOrderNo(String orderNo);

    /**
     * 修改订单信息
     * @param orderInfo
     */
    void updateById(OrderInfo orderInfo);
}
