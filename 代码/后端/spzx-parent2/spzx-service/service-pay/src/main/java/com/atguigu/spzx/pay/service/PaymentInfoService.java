package com.atguigu.spzx.pay.service;

import com.atguigu.spzx.model.entity.pay.PaymentInfo;

import java.util.Map;

/**
 * @Author
 * @Description:
 */
public interface PaymentInfoService {
    //保存支付记录
    PaymentInfo savePaymentInfo(String orderNo);

    /**
     * 支付完成后修改支付状态
     */
    void updatePaymentStatus(Map<String, String> map, Integer payType);
}
