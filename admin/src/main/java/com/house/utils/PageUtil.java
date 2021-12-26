package com.house.utils;

import com.github.pagehelper.PageInfo;
import com.house.vo.resp.PageVo;


/**
 * 分页工具类
 */
public class PageUtil {

    /**
     * 分页数据组装
     * @param pageInfo
     * @param <T>
     * @return
     */
    public static <T>PageVo<T> getPageVo(PageInfo<T> pageInfo){
        PageVo<T> pageInfoPageVo = new PageVo<T>();
        pageInfoPageVo.setTotalRows(pageInfo.getTotal());//总记录数
        pageInfoPageVo.setTotalPages(pageInfo.getPages());//总页数
        pageInfoPageVo.setPageNum(pageInfo.getPageNum());//当前第几页
        pageInfoPageVo.setPageSize(pageInfo.getPageSize());//每页记录数
        pageInfoPageVo.setCurPageSize(pageInfo.getSize());//当前页记录数
        pageInfoPageVo.setRows(pageInfo.getList());//结果集
        return pageInfoPageVo;
    }
}
