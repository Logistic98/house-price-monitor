package com.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.house.dao.SysLogDao;
import com.house.enums.ResponseCode;
import com.house.exception.BusinessException;
import com.house.pojo.SysLog;
import com.house.service.LogService;
import com.house.utils.PageUtil;
import com.house.utils.Response;
import com.house.vo.req.LogPageReqVo;
import com.house.vo.resp.PageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Resource
    private SysLogDao sysLogDao;

    /**
     * 分页与条件查询所有数据
     * @param logPageReqVo
     * @return
     */
    @Override
    public Response<PageVo<SysLog>> pageInfo(LogPageReqVo logPageReqVo) {
        PageHelper.startPage(logPageReqVo.getPageNum(),logPageReqVo.getPageSize());
        List<SysLog> sysLogs = sysLogDao.selectAllLog(logPageReqVo);
        return Response.success(PageUtil.getPageVo(new PageInfo<SysLog>(sysLogs)));
    }

    /**
     * 删除日志
     * @param logIds
     * @return
     */
    @Override
    public Response<String> deletedLog(List<String> logIds) {
        int resultCount = sysLogDao.batchDeletedLog(logIds);
        if (resultCount == 0){
            throw new BusinessException(ResponseCode.OPERATION_ERROR);
        }
        return Response.success(ResponseCode.SUCCESS.getMessage());
    }
}
