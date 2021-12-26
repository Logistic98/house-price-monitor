package com.house.service;

import com.house.utils.Response;
import com.house.vo.req.XxlNewsPageReqVo;
import com.house.vo.resp.XxlNewsPageRespVo;
import com.house.vo.resp.PageVo;

import java.io.IOException;
import java.util.List;

public interface NewsService {
    /**
     * 分页查询xxl-job定时任务消息通知
     * @param xxlNewsPageReqVo
     * @return
     */
    Response<PageVo<XxlNewsPageRespVo>> queryXxlNewsPageInfo(XxlNewsPageReqVo xxlNewsPageReqVo) throws IOException;

    /**
     * 删除消息通知日志
     * @param logIds
     * @return
     */
    Response<String> deleteNewsLog(List<String> logIds);
}
