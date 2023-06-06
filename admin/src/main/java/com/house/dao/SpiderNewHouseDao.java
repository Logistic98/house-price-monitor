package com.house.dao;

import com.house.vo.req.NewHouseReqVo;
import com.house.vo.resp.NewHouseRespVo;

import java.util.List;

public interface SpiderNewHouseDao {

    /**
     * 分页查询新楼盘
     * @param newHouseReqVo
     * @return
     */
    List<NewHouseRespVo> queryNewHouse(NewHouseReqVo newHouseReqVo);
}
