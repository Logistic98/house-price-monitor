package com.house.service;

import com.house.vo.req.UserOwnRoleReqVo;

import java.util.List;

public interface UserRoleService {

    /**
     * 根据用户id 查询用户拥有的角色数据接口
     */
    List<String> getRoleIdsByUserId(String userId);

    /**
     * 更新用户角色
     * @param userOwnRoleReqVo
     * @return
     */
    void addUserRoleInfo(UserOwnRoleReqVo userOwnRoleReqVo);

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
}
