package com.atguigu.spzx.order.service;

import com.atguigu.spzx.model.dto.h5.OrderInfoDto;
import com.atguigu.spzx.model.entity.order.OrderInfo;
import com.atguigu.spzx.model.vo.h5.TradeVo;
import com.github.pagehelper.PageInfo;

/**
 * @Author
 * @Description:
 */
public interface OrderInfoService {
    /**
     * 获取订单确认页信息
     * @return
     */
    TradeVo getTrade();

    /**
     * 提交订单
     * @param orderInfoDto
     * @return
     */
    Long submitOrder(OrderInfoDto orderInfoDto);

    /**
     * 获取订单信息
     * @param orderId
     * @return
     */
    OrderInfo getOrderInfo(Long orderId);

    /**
     * 根据订单id获取订单信息
     * @param skuId
     * @return
     */
    TradeVo buy(Long skuId);

    /**
     * 分页查询订单信息
     * @param page
     * @param limit
     * @param orderStatus
     * @return
     */
    PageInfo<OrderInfo> findOrderPage(Integer page, Integer limit, Integer orderStatus);

    /**
     * 根据订单号查询订单信息
     * @param orderNo
     * @return
     */
    OrderInfo getByOrderNo(String orderNo);

    /**
     * 修改订单状态
     * @param orderNo
     * @param orderStatus
     */
    void updateOrderStatus(String orderNo, Integer orderStatus);
}
