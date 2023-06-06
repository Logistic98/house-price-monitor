package com.house.service;

import com.house.utils.Response;
import com.house.vo.req.CommunityReqVo;
import com.house.vo.req.NewHouseReqVo;
import com.house.vo.req.RentReqVo;
import com.house.vo.req.SecondHandReqVo;
import com.house.vo.resp.*;

public interface HousePriceService {

    /**
     * 分页查询新楼盘
     * @param newHouseReqVo
     * @return
     */
    Response<PageVo<NewHouseRespVo>> queryNewHousePageInfo(NewHouseReqVo newHouseReqVo);

    /**
     * 分页查询小区
     * @param communityReqVo
     * @return
     */
    Response<PageVo<CommunityRespVo>> queryCommunityPageInfo(CommunityReqVo communityReqVo);

    /**
     * 分页查询二手房
     * @param secondHandReqVo
     * @return
     */
    Response<PageVo<SecondHandRespVo>> querySecondHandPageInfo(SecondHandReqVo secondHandReqVo);

    /**
     * 分页查询租房
     * @param rentReqVo
     * @return
     */
    Response<PageVo<RentRespVo>> queryRentPageInfo(RentReqVo rentReqVo);

}
