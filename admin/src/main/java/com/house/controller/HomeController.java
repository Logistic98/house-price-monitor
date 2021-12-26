package com.house.controller;


import com.house.service.HomeService;
import com.house.utils.Response;
import com.house.vo.req.RuntimeSummaryReqVo;
import com.house.vo.resp.RuntimeSummaryRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api")
@Api(value = "主页模块",tags = "主页相关模块")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @ApiOperation(value = "运行数据缓存查询",notes = "查询运行数据缓存的接口")
    @PostMapping("/queryRuntimeSummary")
    public Response<List<RuntimeSummaryRespVo>> queryRuntimeSummary(@RequestBody @Valid RuntimeSummaryReqVo runtimeSummaryReqVo) throws IOException, ClassNotFoundException {
        return homeService.queryRuntimeSummary(runtimeSummaryReqVo);
    }


}
