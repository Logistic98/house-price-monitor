package com.house.service.impl;

import com.alibaba.fastjson.JSON;
import com.house.dao.SpiderRuntimeSummaryDao;
import com.house.service.HomeService;
import com.house.utils.HouseUtils;
import com.house.utils.Response;
import com.house.vo.req.RuntimeSummaryReqVo;
import com.house.vo.resp.RuntimeSummaryRespVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    private SpiderRuntimeSummaryDao spiderRuntimeSummaryDao;

    /**
     * 查询运行数据缓存
     * @return
     */
    @Override
    public Response<List<RuntimeSummaryRespVo>> queryRuntimeSummary(RuntimeSummaryReqVo runtimeSummaryReqVo) throws IOException, ClassNotFoundException {
        List<RuntimeSummaryRespVo> runtimeSummaryRespVo = spiderRuntimeSummaryDao.queryRuntimeSummary(runtimeSummaryReqVo);
        int length = runtimeSummaryRespVo.size();
        for(int i=0; i<length; i++){
            byte [] blobData = runtimeSummaryRespVo.get(i).getData();
            String outputData = JSON.toJSONString(HouseUtils.bytesToMap(blobData));
            runtimeSummaryRespVo.get(i).setOutputData(outputData);
        }
        return Response.success(runtimeSummaryRespVo);
    }


}
