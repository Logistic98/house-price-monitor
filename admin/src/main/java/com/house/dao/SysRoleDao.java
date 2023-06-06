package com.house.dao;

import com.house.pojo.SysRole;
import com.house.vo.req.RolePageReqVo;

import java.util.List;

public interface SysRoleDao {

    /**
     * 角色管理分页数据查询
     * @param rolePageReqVo
     * @return
     */
    List<SysRole> queryRolePageInfo(RolePageReqVo rolePageReqVo);

    /**
     * 新增角色
     * @param record
     * @return
     */
    int insertRoleInfo(SysRole record);

    /**
     * 根据主键id查询
     * @param roleId
     * @return
     */
    SysRole selectByPrimaryKey(String roleId);

    /**
     * 更新角色信息
     * @param sysRole
     * @return
     */
    int updateRoleInfo(SysRole sysRole);

}