package com.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.house.dao.XxlJobLogDao;
import com.house.enums.ResponseCode;
import com.house.exception.BusinessException;
import com.house.service.NewsService;
import com.house.utils.PageUtil;
import com.house.utils.Response;
import com.house.vo.req.XxlNewsPageReqVo;
import com.house.vo.resp.XxlNewsPageRespVo;
import com.house.vo.resp.PageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@Service
public class NewsServiceImpl implements NewsService {

    @Resource
    private XxlJobLogDao xxlJobLogDao;

    /**
     * 分页查询xxl-job定时任务消息通知
     * @param xxlNewsPageReqVo
     * @return
     */
    @Override
    public Response<PageVo<XxlNewsPageRespVo>> queryXxlNewsPageInfo(XxlNewsPageReqVo xxlNewsPageReqVo) throws IOException {
        PageHelper.startPage(xxlNewsPageReqVo.getPageNum(), xxlNewsPageReqVo.getPageSize());
        List<XxlNewsPageRespVo> newsList = xxlJobLogDao.queryXxlNewsPageInfo(xxlNewsPageReqVo);
        return Response.success(PageUtil.getPageVo(new PageInfo<XxlNewsPageRespVo>(newsList)));
    }

    /**
     * 删除消息通知日志
     * @param logIds
     * @return
     */
    @Override
    public Response<String> deleteNewsLog(List<String> logIds) {
        int resultCount = xxlJobLogDao.batchDeleteNewsLog(logIds);
        if (resultCount == 0){
            throw new BusinessException(ResponseCode.OPERATION_ERROR);
        }
        return Response.success(ResponseCode.SUCCESS.getMessage());
    }
}
