package com.house.dao;

import com.house.vo.req.RentReqVo;
import com.house.vo.resp.RentRespVo;

import java.util.List;

public interface SpiderRentDao {

    /**
     * 分页查询租房
     * @param rentReqVo
     * @return
     */
    List<RentRespVo> queryRent(RentReqVo rentReqVo);
}
