package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.order.OrderStatisticsDto;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author
 * @Description:
 */

@Mapper
public interface OrderStatisticsMapper {

    /**
     * 插入订单统计数据
     * @param orderStatistics
     */
     void insert(OrderStatistics orderStatistics);

    List<OrderStatistics> selectList(OrderStatisticsDto orderStatisticsDto);
}