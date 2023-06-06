package com.house.service.impl;

import com.house.dao.SysUserRoleDao;
import com.house.enums.ResponseCode;
import com.house.exception.BusinessException;
import com.house.pojo.SysUserRole;
import com.house.service.UserRoleService;
import com.house.utils.IdWorker;
import com.house.vo.req.UserOwnRoleReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 根据用户id 查询用户拥有的角色数据接口
     * @param userId
     */
    @Override
    public List<String> getRoleIdsByUserId(String userId) {
        return sysUserRoleDao.getRoleIdsByUserId(userId);
    }

    /**
     * 更新用户角色
     * @param userOwnRoleReqVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUserRoleInfo(UserOwnRoleReqVo userOwnRoleReqVo) {
        if (userOwnRoleReqVo.getUserId() == null){
            throw new BusinessException(ResponseCode.DATA_ERROR);
        }
        // 先删除用户原来的拥有的角色id
        sysUserRoleDao.removeRoleIdsByUserId(userOwnRoleReqVo.getUserId());
        if (userOwnRoleReqVo.getRoleIds().isEmpty()){// 为空表示去除该用户所有角色
            return;
        }
        // 批量插入用户角色数据
        List<SysUserRole> list = new ArrayList<>();
        for (String roleId : userOwnRoleReqVo.getRoleIds()){
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setId(String.valueOf(idWorker.nextId()));
            sysUserRole.setUserId(userOwnRoleReqVo.getUserId());
            sysUserRole.setRoleId(roleId);
            sysUserRole.setCreateTime(new Date());
            list.add(sysUserRole);
        }
        int result = sysUserRoleDao.batchInsertUserRole(list);
        if (result == 0){
            throw new BusinessException(ResponseCode.OPERATION_ERROR);
        }
    }

    /**
     * 通过角色 id 集合查询所有的用户 id
     * @param roleIdsByPermissionId
     * @return
     */
    @Override
    public List<String> getUserIdsByRoleIds(List<String> roleIdsByPermissionId) {
        return sysUserRoleDao.getUserIdsByRoleIds(roleIdsByPermissionId);
    }

    /**
     * 通过 单个角色id 查询所有的用户ids
     * @param roleId
     * @return
     */
    @Override
    public List<String> getUserIdsByRoleId(String roleId) {
        return sysUserRoleDao.getUserIdsByRoleId(roleId);
    }

    /**
     * 通过角色id删除用户id
     * @param roleId
     * @return
     */
    @Override
    public int removeUseIdsrRoleId(String roleId) {
        return sysUserRoleDao.removeUseIdsrRoleId(roleId);
    }
}
