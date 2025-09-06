package com.annotation.spzx.common.log.service;

import com.atguigu.spzx.model.entity.system.SysOperLog;

/**
 * @Author
 * @Description:
 */
public interface AsyncOperLogService {   // 保存日志数据
    void saveSysOperLog(SysOperLog sysOperLog) ;
}
