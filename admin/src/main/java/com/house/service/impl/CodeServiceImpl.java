package com.house.service.impl;

import com.house.dao.SysCodeDao;
import com.house.service.CodeService;
import com.house.utils.Response;
import com.house.vo.resp.CodeRespVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {

    @Resource
    private SysCodeDao sysCodeDao;

    /**
     * 查询系统字典
     * @return
     */
    @Override
    public Response<List<CodeRespVo>> querySysCode() {
        List<CodeRespVo> codeRespVo = sysCodeDao.querySysCode();
        return Response.success(codeRespVo);
    }
}
