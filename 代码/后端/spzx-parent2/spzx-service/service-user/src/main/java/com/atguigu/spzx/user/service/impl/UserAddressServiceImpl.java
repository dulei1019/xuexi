package com.atguigu.spzx.user.service.impl;

import com.atguigu.spzx.model.entity.user.UserAddress;
import com.atguigu.spzx.user.mapper.UserAddressMapper;
import com.atguigu.spzx.user.service.UserAddressService;
import com.atguigu.spzx.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author
 * @Description:
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> findUserAddressList() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        return userAddressMapper.findByUserId(userId);
    }

    /**
     * 根据id获取地址信息
     * @param id
     * @return
     */
    @Override
    public UserAddress getById(Long id) {
        return userAddressMapper.getById(id);
    }

    /**
     * 新增地址
     * @param userAddress
     */
    @Override
    public void save(UserAddress userAddress) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        userAddress.setUserId(userId);
        userAddressMapper.save(userAddress);
    }

    /**
     * 删除地址
     * @param id
     */
    @Override
    public void delete(Long id) {
        userAddressMapper.delete(id);
    }



}
