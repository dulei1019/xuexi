package com.atguigu.spzx.user.mapper;

import com.atguigu.spzx.model.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author
 * @Description:
 */
@Mapper
public interface UserAddressMapper {
    List<UserAddress> findByUserId(Long userId);

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
