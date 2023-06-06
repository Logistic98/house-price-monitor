package com.house.controller;

import com.house.aop.annotation.MyLog;
import com.house.pojo.SysLog;
import com.house.service.LogService;
import com.house.utils.Response;
import com.house.vo.req.LogPageReqVo;
import com.house.vo.resp.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "系统管理-日志管理",tags = "日志管理相关接口")
@RestController
@RequestMapping("/api")
public class LogController {

    @Autowired
    private LogService logService;

    @ApiOperation(value = "分页查找操作日志",notes = "分页查找操作日志接口")
    @RequiresPermissions("sys:log:list")
    @PostMapping("/logs")
    public Response<PageVo<SysLog>> pageInfo(@RequestBody LogPageReqVo logPageReqVo){
        return logService.pageInfo(logPageReqVo);
    }

    @ApiOperation(value = "删除日志",notes = "删除日志接口")
    @RequiresPermissions("sys:log:delete")
    @MyLog(title = "系统管理-日志管理",action = "删除日志接口")
    @DeleteMapping("/log")
    public Response<String> deletedLog(@RequestBody @ApiParam(value = "日志id集合") List<String> logIds){
        return logService.deletedLog(logIds);
    }
}
