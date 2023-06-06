package com.house.controller;

import com.house.aop.annotation.MyLog;
import com.house.service.HousePriceService;
import com.house.utils.Response;
import com.house.vo.req.*;
import com.house.vo.resp.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "房价查询",tags = "房价查询相关的接口")
@RestController
@RequestMapping("/api")
public class HousePriceController {

    @Autowired
    private HousePriceService housePriceService;

    @MyLog(title = "房价查询-新楼盘查询",action = "分页查询新楼盘接口")
    @ApiOperation(value = "分页查询新楼盘",notes = "分页查询新楼盘接口")
    @RequiresPermissions("spider:newHouse:list")
    @PostMapping("/newHouse")
    public Response<PageVo<NewHouseRespVo>> queryNewHousePageInfo(@RequestBody @Valid NewHouseReqVo newHouseReqVo){
        return housePriceService.queryNewHousePageInfo(newHouseReqVo);
    }

    @MyLog(title = "房价查询-小区查询",action = "分页查询小区接口")
    @ApiOperation(value = "分页查询小区",notes = "分页查询小区接口")
    @RequiresPermissions("spider:community:list")
    @PostMapping("/community")
    public Response<PageVo<CommunityRespVo>> queryCommunityPageInfo(@RequestBody @Valid CommunityReqVo communityReqVo){
        return housePriceService.queryCommunityPageInfo(communityReqVo);
    }

    @MyLog(title = "房价查询-二手房查询",action = "分页查询二手房接口")
    @ApiOperation(value = "分页查询二手房",notes = "分页查询二手房接口")
    @RequiresPermissions("spider:secondHand:list")
    @PostMapping("/secondHand")
    public Response<PageVo<SecondHandRespVo>> querySecondHandPageInfo(@RequestBody @Valid SecondHandReqVo secondHandReqVo){
        return housePriceService.querySecondHandPageInfo(secondHandReqVo);
    }

    @MyLog(title = "房价查询-租房查询",action = "分页查询租房接口")
    @ApiOperation(value = "分页查询租房",notes = "分页查询租房接口")
    @RequiresPermissions("spider:rent:list")
    @PostMapping("/rent")
    public Response<PageVo<RentRespVo>> queryRentPageInfo(@RequestBody @Valid RentReqVo rentReqVo){
        return housePriceService.queryRentPageInfo(rentReqVo);
    }

}
