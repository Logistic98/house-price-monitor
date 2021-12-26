package com.house.dao;

import com.house.pojo.SysLog;
import com.house.vo.req.LogPageReqVo;

import java.util.List;

public interface SysLogDao {

    /**
     * 日志入库
     * @param record
     * @return
     */
    int insertLogInfo(SysLog record);

    /**
     * 分页与条件查询所有数据
     * @param logPageReqVo
     * @return
     */
    List<SysLog> selectAllLog(LogPageReqVo logPageReqVo);

    /**
     * 删除日志（包括批量删除）
     * @param logIds
     * @return
     */
    int batchDeletedLog(List<String> logIds);
}