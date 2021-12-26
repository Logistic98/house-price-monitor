package com.xxljob.service;


import com.xxljob.utils.Response;
import com.xxljob.vo.resp.CityAvgHousePriceRespVo;
import com.xxljob.vo.resp.HouseCountRespVo;
import com.xxljob.vo.resp.TimeAvgHousePriceRespVo;

import java.util.List;

public interface SpiderRuntimeSummaryService {

    /**
     * 查询房源数量
     * @return
     */
    Response<List<HouseCountRespVo>> queryHouseCount();

    /**
     * 查询各城市房源平均价格
     * @return
     */
    Response<List<CityAvgHousePriceRespVo>> queryCityAvgHousePrice();

    /**
     * 查询某时限范围里房源平均价格趋势
     * @return
     */
    Response<List<TimeAvgHousePriceRespVo>> queryTimeAvgHousePrice();




}
