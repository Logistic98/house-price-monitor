package com.house.dao;


import com.house.vo.req.RuntimeSummaryReqVo;
import com.house.vo.resp.RuntimeSummaryRespVo;

import java.util.List;

public interface SpiderRuntimeSummaryDao {


    /**
     * 查询运行数据缓存
     * @param
     * @return
     */
    List<RuntimeSummaryRespVo> queryRuntimeSummary(RuntimeSummaryReqVo runtimeSummaryReqVo);


}