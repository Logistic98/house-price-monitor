package com.house.dao;

import com.house.vo.resp.CodeRespVo;

import java.util.List;

public interface SysCodeDao {

    /**
     * 查询系统字典
     * @param
     * @return
     */
    List<CodeRespVo> querySysCode();

}