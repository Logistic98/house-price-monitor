package com.house.service;


import com.house.utils.Response;
import com.house.vo.req.RuntimeSummaryReqVo;
import com.house.vo.resp.RuntimeSummaryRespVo;

import java.io.IOException;
import java.util.List;

public interface HomeService {

    /**
     * 查询运行数据缓存
     * @return
     */
    Response<List<RuntimeSummaryRespVo>> queryRuntimeSummary(RuntimeSummaryReqVo runtimeSummaryReqVo) throws IOException, ClassNotFoundException;

}
