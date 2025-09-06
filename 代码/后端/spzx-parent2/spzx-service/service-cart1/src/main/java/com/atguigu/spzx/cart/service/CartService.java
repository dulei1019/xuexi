package com.atguigu.spzx.cart.service;

import com.atguigu.spzx.model.entity.h5.CartInfo;

import java.util.List;

/**
 * @Author
 * @Description:
 */
public interface CartService {
    /**
     * 添加商品到购物车
     * @param skuId
     * @param skuNum
     */
    void addToCart(Long skuId, Integer skuNum);

    /**
     * 查询购物车
     * @return
     */
    List<CartInfo> getCartList();

    /**
     * 删除购物车商品
     * @param skuId
     */
    void deleteCart(Long skuId);

    /**
     * 更新购物车商品选中状态
     * @param skuId
     * @param isChecked
     */
    void checkCart(Long skuId, Integer isChecked);

    /**
     * 全选/全不选
     * @param isChecked
     */
    void allCheckCart(Integer isChecked);

    /**
     * 清空购物车
     */
    void clearCart();



    List<CartInfo> getAllCkecked();

    void deleteChecked();
}
