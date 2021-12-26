package com.house.service.impl;

import com.house.dao.SysRolePermissionDao;
import com.house.enums.ResponseCode;
import com.house.exception.BusinessException;
import com.house.pojo.SysRolePermission;
import com.house.service.RolePermissionService;
import com.house.utils.IdWorker;
import com.house.vo.req.RolePermissionOperationReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Resource
    private SysRolePermissionDao sysRolePermissionDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 插入角色权限关联表数据
     * @param operationReqVo
     */
    @Override
    public void addRolePermission(RolePermissionOperationReqVo operationReqVo) {
        if (null == operationReqVo.getPermissionIds()){
            throw new BusinessException(ResponseCode.DATA_ERROR);
        }
        // 添加之前，先删除原来角色 id 对应的所有权限id
        sysRolePermissionDao.removeByRoleId(operationReqVo.getRoleId());
        // 权限id集合为空，表示删除该角色对应的所有权限
        if (operationReqVo.getPermissionIds().isEmpty()){
            return;
        }
        Date date = new Date();
        List<SysRolePermission> list = new ArrayList<>();
        for (String permissionId : operationReqVo.getPermissionIds()){
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setId(String.valueOf(idWorker.nextId()));
            sysRolePermission.setRoleId(operationReqVo.getRoleId());
            sysRolePermission.setPermissionId(permissionId);
            sysRolePermission.setCreateTime(date);
            list.add(sysRolePermission);
        }
        int result = sysRolePermissionDao.batchRolePermission(list);
        if (result == 0){
            throw new BusinessException(ResponseCode.OPERATION_ERROR);
        }
    }

    /**
     * 通过权限 id 查询所有的角色 id
     * @param permissionId
     * @return
     */
    @Override
    public List<String> getRoleIdsByPermissionId(String permissionId) {
        return sysRolePermissionDao.getRoleIdsByPermissionId(permissionId);
    }

    /**
     * 通过权限id删除相关角色和该菜单权限的关联表信息
     * @param permissionId
     */
    @Override
    public int removeRoleByPermissionId(String permissionId) {
        return sysRolePermissionDao.removeByPermissionId(permissionId);
    }

    /**
     * 通过角色id查询拥有的权限id
     * @param roleId
     */
    @Override
    public List<String> getPermissionIdsByRoleId(String roleId) {
        return sysRolePermissionDao.getPermissionIdsByRoleId(roleId);
    }

    /**
     * 通过角色id删除权限id
     * @param roleId
     * @return
     */
    @Override
    public int removeByRoleId(String roleId) {
        return sysRolePermissionDao.removeByRoleId(roleId);
    }
}
