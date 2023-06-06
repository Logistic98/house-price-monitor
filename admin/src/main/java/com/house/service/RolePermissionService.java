package com.house.service;

import com.house.vo.req.RolePermissionOperationReqVo;

import java.util.List;

public interface RolePermissionService {

    /**
     * 插入角色权限关联表数据
     */
    void addRolePermission(RolePermissionOperationReqVo operationReqVo);

    /**
     * 通过权限 id 查询所有的角色 id
     * @param permissionId
     * @return
     */
    List<String> getRoleIdsByPermissionId(String permissionId);

    /**
     * 通过权限id删除相关角色和该菜单权限的关联表信息
     * @param permissionId
     */
    int removeRoleByPermissionId(String permissionId);

    /**
     * 通过角色id查询拥有的权限id
     * @param roleId
     */
    List<String> getPermissionIdsByRoleId(String roleId);

    /**
     * 通过角色id删除权限id
     * @param roleId
     * @return
     */
    int removeByRoleId(String roleId);
}
