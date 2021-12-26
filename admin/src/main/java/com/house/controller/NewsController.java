package com.house.controller;

import com.house.aop.annotation.MyLog;
import com.house.service.NewsService;
import com.house.utils.Response;
import com.house.vo.req.XxlNewsPageReqVo;
import com.house.vo.resp.XxlNewsPageRespVo;
import com.house.vo.resp.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Api(value = "消息通知",tags = "消息通知相关的接口")
@RestController
@RequestMapping("/api")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @MyLog(title = "消息通知-定时任务通知",action = "分页查询xxl-job定时任务消息通知接口")
    @ApiOperation(value = "分页查询定时任务消息通知",notes = "分页查询xxl-job定时任务消息通知接口")
    @PostMapping("/xxlnews")
    public Response<PageVo<XxlNewsPageRespVo>> queryXxlNewsPageInfo(@RequestBody @Valid XxlNewsPageReqVo xxlNewsPageReqVo) throws IOException {
        return newsService.queryXxlNewsPageInfo(xxlNewsPageReqVo);
    }

    @MyLog(title = "消息通知-定时任务通知",action = "删除消息通知日志接口")
    @ApiOperation(value = "删除消息通知日志",notes = "删除消息通知日志接口")
    @DeleteMapping("/deleteNewsLog")
    public Response<String> deletedLog(@RequestBody @ApiParam(value = "日志id集合") List<String> logIds){
        return newsService.deleteNewsLog(logIds);
    }
}
