package com.house.service;

import com.house.utils.Response;
import com.house.vo.resp.CodeRespVo;

import java.util.List;

public interface CodeService {

    /**
     * 查询系统字典
     * @return
     */
    Response<List<CodeRespVo>> querySysCode();
}
