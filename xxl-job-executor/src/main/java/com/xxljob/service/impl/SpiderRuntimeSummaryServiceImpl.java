package com.xxljob.service.impl;

import com.xxljob.dao.SpiderRuntimeSummaryDao;
import com.xxljob.service.SpiderRuntimeSummaryService;
import com.xxljob.utils.Response;
import com.xxljob.vo.resp.CityAvgHousePriceRespVo;
import com.xxljob.vo.resp.HouseCountRespVo;
import com.xxljob.vo.resp.TimeAvgHousePriceRespVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpiderRuntimeSummaryServiceImpl implements SpiderRuntimeSummaryService {

    @Resource
    private SpiderRuntimeSummaryDao spiderRuntimeSummaryDao;

    /**
     * 查询房源数量
     * @return
     */
    @Override
    public Response<List<HouseCountRespVo>> queryHouseCount() {
        List<HouseCountRespVo> houseCountRespVo = spiderRuntimeSummaryDao.queryHouseCount();
        return Response.success(houseCountRespVo);
    }

    /**
     * 查询各城市房源平均价格
     * @return
     */
    @Override
    public Response<List<CityAvgHousePriceRespVo>> queryCityAvgHousePrice() {
        List<CityAvgHousePriceRespVo> cityAvgHousePriceRespVo = spiderRuntimeSummaryDao.queryCityAvgHousePrice();
        return Response.success(cityAvgHousePriceRespVo);
    }

    /**
     * 查询某时限范围里房源平均价格趋势
     * @return
     */
    @Override
    public Response<List<TimeAvgHousePriceRespVo>> queryTimeAvgHousePrice() {
        List<TimeAvgHousePriceRespVo> timeAvgHousePriceRespVo = spiderRuntimeSummaryDao.queryTimeAvgHousePrice();
        return Response.success(timeAvgHousePriceRespVo);
    }

}
