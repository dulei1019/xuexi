package com.atguigu.spzx.user.service;

import com.atguigu.spzx.model.entity.user.UserAddress;

import java.util.List;

/**
 * @Author
 * @Description:
 */
public interface UserAddressService {

    List<UserAddress> findUserAddressList();

    /**
     * 根据id查询地址信息
     * @param id
     * @return
     */
    UserAddress getById(Long id);

    /**
     * 新增地址
     * @param userAddress
     */
    void save(UserAddress userAddress);

    void delete(Long id);
}
