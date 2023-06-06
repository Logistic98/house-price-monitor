package com.xxljob.dao;


import com.xxljob.pojo.SpiderRuntimeSummary;
import com.xxljob.vo.resp.CityAvgHousePriceRespVo;
import com.xxljob.vo.resp.HouseCountRespVo;
import com.xxljob.vo.resp.TimeAvgHousePriceRespVo;

import java.util.List;

public interface SpiderRuntimeSummaryDao {

    /**
     * 查询房源数量
     * @param
     * @return
     */
    List<HouseCountRespVo> queryHouseCount();

    /**
     * 查询各城市房源平均价格
     * @param
     * @return
     */
    List<CityAvgHousePriceRespVo> queryCityAvgHousePrice();

    /**
     * 查询某时限范围里房源平均价格趋势
     * @param
     * @return
     */
    List<TimeAvgHousePriceRespVo> queryTimeAvgHousePrice();

    /**
     * 新增运行数据缓存
     * @param record
     * @return
     */
    int insertRuntimeSummaryData(SpiderRuntimeSummary record);

    /**
     * 删除指定方法的运行数据（先删后插）
     * @param
     * @return
     */
    int deleteRuntimeSummaryData(String method);



}