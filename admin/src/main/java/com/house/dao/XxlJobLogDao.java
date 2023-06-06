package com.house.dao;

import com.house.vo.req.XxlNewsPageReqVo;
import com.house.vo.resp.XxlNewsPageRespVo;

import java.util.List;

public interface XxlJobLogDao {

    /**
     * 分页查询xxl-job定时任务消息通知
     * @param xxlNewsPageReqVo
     * @return
     */
    List<XxlNewsPageRespVo> queryXxlNewsPageInfo(XxlNewsPageReqVo xxlNewsPageReqVo);

    /**
     * 删除消息通知日志（包括批量删除）
     * @param logIds
     * @return
     */
    int batchDeleteNewsLog(List<String> logIds);
}