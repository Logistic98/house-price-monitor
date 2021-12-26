package com.house.service;

import com.house.pojo.SysRole;
import com.house.utils.Response;
import com.house.vo.req.RolePageReqVo;
import com.house.vo.req.RoleReqVo;
import com.house.vo.req.RoleUpdateReqVo;
import com.house.vo.resp.PageVo;

import java.util.List;
import java.util.Set;

public interface RoleService {

    /**
     * 分页获取角色数据
     * @return
     */
    Response<PageVo> pageInfoRoles(RolePageReqVo rolePageReqVo);

    /**
     * 新增角色
     * @param roleReqVo
     * @return
     */
    Response<String> createRole(RoleReqVo roleReqVo);

    /**
     * 获取角色详情数据
     * @param roleId
     * @return
     */
    Response<Set<String>> detailInfo(String roleId);

    /**
     * 更新角色信息
     * @param roleUpdateReqVo
     * @return
     */
    Response<String> updateRole(RoleUpdateReqVo roleUpdateReqVo);

    /**
     * 更新角色状态
     * @param roleId
     * @param status
     * @return
     */
    Response<String> updateRoleStatus(String roleId, Integer status);

    /**
     * 删除角色信息
     * @param roleId
     * @return
     */
    Response<String> deletedRole(String roleId);

    /**
     * 查询所有权限
     * @return
     */
    List<SysRole> queryRolePageInfo();

}
