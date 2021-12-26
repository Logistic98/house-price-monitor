package com.house.dao;

import com.house.pojo.SysUserRole;

import java.util.List;

public interface SysUserRoleDao {

    /**
     * 根据用户id 查询用户拥有的角色数据
     */
    List<String> getRoleIdsByUserId(String userId);

    /**
     * 根据用户id 删除用户拥有的角色数据
     * @param userId
     */
    int removeRoleIdsByUserId(String userId);

    /**
     * 批量插入用户角色数据
     * @param list
     * @return
     */
    int batchInsertUserRole(List<SysUserRole> list);

    /**
     * 通过角色 id 集合查询所有的用户 id
     * @param roleIdsByPermissionId
     * @return
     */
    List<String> getUserIdsByRoleIds(List<String> roleIdsByPermissionId);

    /**
     * 通过 单个角色id 查询所有的用户ids
     * @param roleId
     * @return
     */
    List<String> getUserIdsByRoleId(String roleId);

    /**
     * 通过角色id删除用户id
     * @param roleId
     * @return
     */
    int removeUseIdsrRoleId(String roleId);

    //================== 权限数据 ====================

    /**
     * 通过用户id获取该用户所拥有的角色名称（用户登录时查询拥有角色）
     * @param userId
     * @return
     */
    List<String> getRoleNameByUserId(String userId);

    /**
     * 通过用户id获取该用户所拥有的权限授权 如：sys:user:add
     * @param userId
     * @return
     */
    List<String> getPermissionPermsByUserId(String userId);

    /**
     * 查询前端按钮权限
     * @param id
     * @return
     */
    List<String> getPermissionCodesByUserId(String id);
}