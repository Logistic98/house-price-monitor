package com.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.house.dao.*;
import com.house.service.HousePriceService;
import com.house.utils.PageUtil;
import com.house.utils.Response;
import com.house.vo.req.*;
import com.house.vo.resp.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HousePriceServiceImpl implements HousePriceService {

    @Resource
    private SpiderNewHouseDao spiderNewHouseDao;

    @Resource
    private SpiderCommunityDao spiderCommunityDao;

    @Resource
    private SpiderSecondHandDao SpiderSecondHandDao;

    @Resource
    private SpiderRentDao spiderRentDao;

    /**
     * 分页查询新楼盘
     * @param newHouseReqVo
     * @return
     */
    @Override
    public Response<PageVo<NewHouseRespVo>> queryNewHousePageInfo(NewHouseReqVo newHouseReqVo) {
        PageHelper.startPage(newHouseReqVo.getPageNum(),newHouseReqVo.getPageSize());
        List<NewHouseRespVo> newHouses = spiderNewHouseDao.queryNewHouse(newHouseReqVo);
        return Response.success(PageUtil.getPageVo(new PageInfo<NewHouseRespVo>(newHouses)));
    }

    /**
     * 分页查询小区
     * @param communityReqVo
     * @return
     */
    @Override
    public Response<PageVo<CommunityRespVo>> queryCommunityPageInfo(CommunityReqVo communityReqVo) {
        PageHelper.startPage(communityReqVo.getPageNum(),communityReqVo.getPageSize());
        List<CommunityRespVo> communities = spiderCommunityDao.queryCommunity(communityReqVo);
        return Response.success(PageUtil.getPageVo(new PageInfo<CommunityRespVo>(communities)));
    }

    /**
     * 分页查询二手房
     * @param secondHandReqVo
     * @return
     */
    @Override
    public Response<PageVo<SecondHandRespVo>> querySecondHandPageInfo(SecondHandReqVo secondHandReqVo) {
        PageHelper.startPage(secondHandReqVo.getPageNum(),secondHandReqVo.getPageSize());
        List<SecondHandRespVo> secondHands = SpiderSecondHandDao.querySecondHand(secondHandReqVo);
        return Response.success(PageUtil.getPageVo(new PageInfo<SecondHandRespVo>(secondHands)));
    }

    /**
     * 分页查询租房
     * @param rentReqVo
     * @return
     */
    @Override
    public Response<PageVo<RentRespVo>> queryRentPageInfo(RentReqVo rentReqVo) {
        PageHelper.startPage(rentReqVo.getPageNum(),rentReqVo.getPageSize());
        List<RentRespVo> rents = spiderRentDao.queryRent(rentReqVo);
        return Response.success(PageUtil.getPageVo(new PageInfo<RentRespVo>(rents)));
    }
}
