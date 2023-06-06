package com.house.controller;

import com.house.service.CodeService;
import com.house.utils.Response;
import com.house.vo.resp.CodeRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "系统字典查询",tags = "系统字典查询接口")
@RestController
@RequestMapping("/api")
public class CodeController {

    @Autowired
    private CodeService codeService;

    @ApiOperation(value = "系统字典查询",notes = "查询系统字典的接口")
    @PostMapping("/code")
    public Response<List<CodeRespVo>> querySysCode(){
        return codeService.querySysCode();
    }
}
