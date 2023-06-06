package com.house.dao;

import com.house.vo.req.SecondHandReqVo;
import com.house.vo.resp.SecondHandRespVo;

import java.util.List;

public interface SpiderSecondHandDao {

    /**
     * 分页查询二手房
     * @param secondHandReqVo
     * @return
     */
    List<SecondHandRespVo> querySecondHand(SecondHandReqVo secondHandReqVo);
}
