package com.house.service;

import com.house.pojo.SysLog;
import com.house.utils.Response;
import com.house.vo.req.LogPageReqVo;
import com.house.vo.resp.PageVo;

import java.util.List;

public interface LogService {

    /**
     * 分页与条件查询所有数据
     * @param logPageReqVo
     * @return
     */
    Response<PageVo<SysLog>> pageInfo(LogPageReqVo logPageReqVo);

    /**
     * 删除日志
     * @param logIds
     * @return
     */
    Response<String> deletedLog(List<String> logIds);
}
