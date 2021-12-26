package com.xxljob.executor;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.xxljob.dao.SpiderRuntimeSummaryDao;
import com.xxljob.enums.ResponseCode;
import com.xxljob.exception.BusinessException;
import com.xxljob.pojo.SpiderRuntimeSummary;
import com.xxljob.service.SpiderRuntimeSummaryService;
import com.xxljob.utils.CommonUtils;
import com.xxljob.utils.Response;
import com.xxljob.vo.resp.CityAvgHousePriceRespVo;
import com.xxljob.vo.resp.HouseCountRespVo;
import com.xxljob.vo.resp.TimeAvgHousePriceRespVo;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * xxl-job执行器
 */
@Component
public class XxlJobTimeTask {
    private static Logger logger = LoggerFactory.getLogger(XxlJobTimeTask.class);

    @Autowired
    private SpiderRuntimeSummaryService spiderRuntimeSummaryService;

    @Resource
    private SpiderRuntimeSummaryDao spiderRuntimeSummaryDao;

    /**
     *仪表盘统计信息汇总
     */
    @XxlJob("dashboardJobHandler")
    public void dashboardJobHandler() throws Exception {

        XxlJobHelper.log("[1] 开始汇总：房源数量数据");
        Date startTime = DateUtil.date();

        Response<List<HouseCountRespVo>> houseCount = spiderRuntimeSummaryService.queryHouseCount();

        String updateTime = CommonUtils.getCurrentTimeString();
        String method = "queryHouseCount";
        String desc = "查询房源数量";
        String timeRange = "day";

        spiderRuntimeSummaryDao.deleteRuntimeSummaryData(method);

        int length = houseCount.getData().size();
        for(int i=0; i<length; i++){

            String city = houseCount.getData().get(i).getCity();
            String count = houseCount.getData().get(i).getCount();
            String type = houseCount.getData().get(i).getType();

            Map<String, String> map = new HashMap<>();
            map.put("count",count);
            byte[] data = CommonUtils.mapToBytes(map);

            SpiderRuntimeSummary spiderRuntimeSummary = new SpiderRuntimeSummary();
            spiderRuntimeSummary.setMethod(method);
            spiderRuntimeSummary.setDesc(desc);
            spiderRuntimeSummary.setData(data);
            spiderRuntimeSummary.setCity(city);
            spiderRuntimeSummary.setType(type);
            spiderRuntimeSummary.setUpdateTime(updateTime);
            spiderRuntimeSummary.setTimeRange(timeRange);

            if (spiderRuntimeSummaryDao.insertRuntimeSummaryData(spiderRuntimeSummary) != 1){
                throw new BusinessException(ResponseCode.OPERATION_ERROR);
            }

        }

        Date endTime = DateUtil.date();
        long timeSpent = DateUtil.between(startTime, endTime, DateUnit.MS);
        XxlJobHelper.log("汇总完毕：房源数量数据，用时【" + timeSpent + "毫秒】");

        //////////////////////////////////////////////////////////////////////////////////////

        XxlJobHelper.log("[2] 开始汇总：各城市房源平均价格数据");
        startTime = DateUtil.date();

        Response<List<CityAvgHousePriceRespVo>> cityAvgHousePrice = spiderRuntimeSummaryService.queryCityAvgHousePrice();

        updateTime = CommonUtils.getCurrentTimeString();
        method = "queryCityAvgHousePrice";
        desc = "查询各城市房源平均价格";

        spiderRuntimeSummaryDao.deleteRuntimeSummaryData(method);

        length = cityAvgHousePrice.getData().size();
        for(int i=0; i<length; i++){

            timeRange = cityAvgHousePrice.getData().get(i).getTimeRange();
            String avgPrice = cityAvgHousePrice.getData().get(i).getAvgPrice();
            String city = cityAvgHousePrice.getData().get(i).getCity();
            String type = cityAvgHousePrice.getData().get(i).getType();

            Map<String, String> map = new HashMap<>();
            map.put("avgPrice",avgPrice);
            byte[] data = CommonUtils.mapToBytes(map);

            SpiderRuntimeSummary spiderRuntimeSummary = new SpiderRuntimeSummary();
            spiderRuntimeSummary.setMethod(method);
            spiderRuntimeSummary.setDesc(desc);
            spiderRuntimeSummary.setData(data);
            spiderRuntimeSummary.setCity(city);
            spiderRuntimeSummary.setType(type);
            spiderRuntimeSummary.setUpdateTime(updateTime);
            spiderRuntimeSummary.setTimeRange(timeRange);

            if (spiderRuntimeSummaryDao.insertRuntimeSummaryData(spiderRuntimeSummary) != 1){
                throw new BusinessException(ResponseCode.OPERATION_ERROR);
            }

        }

        endTime = DateUtil.date();
        timeSpent = DateUtil.between(startTime, endTime, DateUnit.MS);
        XxlJobHelper.log("汇总完毕：各城市房源平均价格数据，用时【" + timeSpent + "毫秒】");

        //////////////////////////////////////////////////////////////////////////////////////

        XxlJobHelper.log("[3] 开始汇总：某时限范围里房源平均价格趋势数据");
        startTime = DateUtil.date();

        Response<List<TimeAvgHousePriceRespVo>> timeAvgHousePrice = spiderRuntimeSummaryService.queryTimeAvgHousePrice();

        updateTime = CommonUtils.getCurrentTimeString();
        method = "queryTimeAvgHousePrice";
        desc = "查询某时限范围里房源平均价格趋势";

        spiderRuntimeSummaryDao.deleteRuntimeSummaryData(method);

        length = timeAvgHousePrice.getData().size();
        for(int i=0; i<length; i++){

            timeRange = timeAvgHousePrice.getData().get(i).getTimeRange();
            String avgPrice = timeAvgHousePrice.getData().get(i).getAvgPrice();
            String city = timeAvgHousePrice.getData().get(i).getCity();
            String type = timeAvgHousePrice.getData().get(i).getType();
            String date = timeAvgHousePrice.getData().get(i).getDate();

            Map<String, String> map = new HashMap<>();
            map.put("avgPrice",avgPrice);
            map.put("date",date);
            byte[] data = CommonUtils.mapToBytes(map);

            SpiderRuntimeSummary spiderRuntimeSummary = new SpiderRuntimeSummary();
            spiderRuntimeSummary.setMethod(method);
            spiderRuntimeSummary.setDesc(desc);
            spiderRuntimeSummary.setData(data);
            spiderRuntimeSummary.setCity(city);
            spiderRuntimeSummary.setType(type);
            spiderRuntimeSummary.setUpdateTime(updateTime);
            spiderRuntimeSummary.setTimeRange(timeRange);

            if (spiderRuntimeSummaryDao.insertRuntimeSummaryData(spiderRuntimeSummary) != 1){
                throw new BusinessException(ResponseCode.OPERATION_ERROR);
            }

        }

        endTime = DateUtil.date();
        timeSpent = DateUtil.between(startTime, endTime, DateUnit.MS);
        XxlJobHelper.log("汇总完毕：某时限范围里房源平均价格趋势数据，用时【" + timeSpent + "毫秒】");

    }
}
