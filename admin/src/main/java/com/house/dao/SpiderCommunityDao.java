package com.house.dao;

import com.house.vo.req.*;
import com.house.vo.resp.*;

import java.util.List;

public interface SpiderCommunityDao {

    /**
     * 分页查询小区
     * @param communityReqVo
     * @return
     */
    List<CommunityRespVo> queryCommunity(CommunityReqVo communityReqVo);
}
