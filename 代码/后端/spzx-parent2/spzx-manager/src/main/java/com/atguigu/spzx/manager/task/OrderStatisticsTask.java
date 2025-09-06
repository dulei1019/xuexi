package com.atguigu.spzx.manager.task;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.atguigu.spzx.manager.mapper.OrderInfoMapper;
import com.atguigu.spzx.manager.mapper.OrderStatisticsMapper;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author
 * @Description:
 */
@Component
public class OrderStatisticsTask {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;


    //每天凌晨2点，查询前一天的统计数据，把统计的结果保存到数据库中
    @Scheduled(cron = "0 0 2 * * ?")
    public void orderTotalAmountStatistics(){
        //1.获取前一天的日期
        String createDate = DateUtil.offsetDay(DateUtil.date(), -1).toString("yyyy-MM-dd");
        //2.根据前一天的日期进行统计功能
        //统计前一天的订单总金额
        OrderStatistics orderStatistics = orderInfoMapper.selectStatisticsByDate(createDate);
        //把统计之后的数据，添加到统计结果表里面
        if (orderStatistics !=null){
            orderStatisticsMapper.insert(orderStatistics);
        }
    }



}
