package com.atguigu.spzx.fegin.order;

import com.atguigu.spzx.model.entity.order.OrderInfo;
import com.atguigu.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author
 * @Description:
 */
@FeignClient(value = "service-order")
public interface OrderFeginClient {


    @GetMapping("/api/order/orderInfo/auth/getOrderInfoByOrderNo/{orderNo}")
    public Result<OrderInfo> getOrderInfoByOrderNo(@PathVariable String orderNo);

    @GetMapping("auth/updateOrderStatusPayed/{orderNo}/{orderStatus}")
    public Result updateOrderStatus(@PathVariable(value = "orderNo") String orderNo , @PathVariable(value = "orderStatus") Integer orderStatus);


}
