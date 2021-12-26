package com.house.service;

import com.house.pojo.SysPermission;
import com.house.utils.Response;
import com.house.vo.req.PermissionAddReqVo;
import com.house.vo.req.PermissionUpdateReqVo;
import com.house.vo.resp.PermissionRespNodeTreeVo;
import com.house.vo.resp.PermissionRespNodeVo;

import java.util.List;

public interface PermissionService {

    /**
     * 用户id查询用户拥有的权限菜单列表
     * @param userId
     * @return
     */
    List<PermissionRespNodeVo> permissionTreeListByUserId(String userId);

    /**查询所有权限列表（表格展示）
     * 树形表格结果数据组装
     * @return
     */
    List<SysPermission> selectAll();

    /**
     * 添加权限的上级选择权限树结构展示（递归）
     * @return
     */
    Response<List<PermissionRespNodeTreeVo>> selectAllMenuByTree();

    /**
     * 新增权限
     * @param permissionAddReqVO
     * @return
     */
    Response addPermission(PermissionAddReqVo permissionAddReqVO);

    /**
     * 获取所有权限（包括按钮），角色添加/编辑/分配权限时用到的结果数据
     * @return
     */
    List<PermissionRespNodeVo> selectAllTree();

    /**
     * 更新权限
     * @param permissionUpdateReqVo
     * @return
     */
    Response<String> updatePermission(PermissionUpdateReqVo permissionUpdateReqVo);

    /**
     * 删除菜单权限
     * @param permissionId
     * @return
     */
    Response<String> deletedPermission(String permissionId);
}
