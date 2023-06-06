package com.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.house.constant.Constant;
import com.house.dao.SysRoleDao;
import com.house.enums.ResponseCode;
import com.house.exception.BusinessException;
import com.house.pojo.SysRole;
import com.house.service.RedisService;
import com.house.service.RolePermissionService;
import com.house.service.RoleService;
import com.house.service.UserRoleService;
import com.house.utils.IdWorker;
import com.house.utils.PageUtil;
import com.house.utils.Response;
import com.house.vo.req.RolePageReqVo;
import com.house.vo.req.RolePermissionOperationReqVo;
import com.house.vo.req.RoleReqVo;
import com.house.vo.req.RoleUpdateReqVo;
import com.house.vo.resp.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Resource
    private SysRoleDao sysRoleDao;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 角色管理分页数据
     * @param rolePageReqVo
     * @return
     */
    @Override
    public Response<PageVo> pageInfoRoles(RolePageReqVo rolePageReqVo) {
        PageHelper.startPage(rolePageReqVo.getPageNum(),rolePageReqVo.getPageSize());
        List<SysRole> sysRoles = sysRoleDao.queryRolePageInfo(rolePageReqVo);
        return Response.success(PageUtil.getPageVo(new PageInfo<SysRole>(sysRoles)));
    }

    /**
     * 新增角色
     * @param roleReqVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> createRole(RoleReqVo roleReqVo) {
        if (null == roleReqVo.getPermissionsIds() || roleReqVo.getPermissionsIds().isEmpty()){
            throw new BusinessException(ResponseCode.DATA_ERROR);
        }
        String roleId = String.valueOf(idWorker.nextId());
        // 角色入库
        SysRole sysRole = SysRole.builder()
                .id(roleId)
                .name(roleReqVo.getName())
                .status(roleReqVo.getStatus())
                .description(roleReqVo.getDescription())
                .createTime(new Date())
                .deleted(Constant.Deleted.NOT_DELETED)
                .build();
        int addCount = sysRoleDao.insertRoleInfo(sysRole);
        if (addCount != 1){
            throw new BusinessException(ResponseCode.OPERATION_ERROR);
        }
        // 添加 角色 权限关联表 数据
        RolePermissionOperationReqVo rolePermissionOperationReqVo = new RolePermissionOperationReqVo();
        rolePermissionOperationReqVo.setRoleId(roleId);
        rolePermissionOperationReqVo.setPermissionIds(roleReqVo.getPermissionsIds());
        rolePermissionService.addRolePermission(rolePermissionOperationReqVo);
        return Response.success(ResponseCode.SUCCESS.getMessage());
    }

    /**
     * 获取角色详情数据
     * @param roleId
     * @return
     */
    @Override
    public Response<Set<String>> detailInfo(String roleId) {
        if (StringUtils.isBlank(roleId)){
            throw new BusinessException(ResponseCode.DATA_ERROR);
        }
        SysRole sysRole = sysRoleDao.selectByPrimaryKey(roleId);
        if (sysRole == null){
            logger.info("传入的id:{}不合法",roleId);
            throw new BusinessException(ResponseCode.DATA_ERROR);
        }
        // 通过角色id查询拥有的权限ids
        List<String> permissionIdsByRoleId = rolePermissionService.getPermissionIdsByRoleId(roleId);
        Set<String> permissionIds = new HashSet<>(permissionIdsByRoleId);
        return Response.success(permissionIds);
    }

    /**
     * 更新角色信息
     * @param roleUpdateReqVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> updateRole(RoleUpdateReqVo roleUpdateReqVo) {
        SysRole sysRole = sysRoleDao.selectByPrimaryKey(roleUpdateReqVo.getId());
        if (sysRole == null){
            throw new BusinessException(ResponseCode.DATA_ERROR);
        }
        BeanUtils.copyProperties(roleUpdateReqVo,sysRole);
        sysRole.setUpdateTime(new Date());
        int updateCount = sysRoleDao.updateRoleInfo(sysRole);
        if (updateCount != 1){
            throw new BusinessException(ResponseCode.OPERATION_ERROR);
        }
        // 更新 角色 权限 关联表
        RolePermissionOperationReqVo rolePermissionOperationReqVo = new RolePermissionOperationReqVo();
        rolePermissionOperationReqVo.setRoleId(roleUpdateReqVo.getId());
        rolePermissionOperationReqVo.setPermissionIds(roleUpdateReqVo.getPermissionsIds());
        rolePermissionService.addRolePermission(rolePermissionOperationReqVo);
        // 标记关联用户主动去刷新
        // 通过 角色id 查询所有的用户ids
        List<String> userIdsByRoleId = userRoleService.getUserIdsByRoleId(roleUpdateReqVo.getId());
        if (null != userIdsByRoleId && !userIdsByRoleId.isEmpty()){
            for (String userId : userIdsByRoleId){
                /**
                 * 标记用户需要重新登录,禁止再访问我们的系统资源
                 */
                redisService.set(Constant.JWT_USER_LOGIN_BLACKLIST+userId,userId);
                /**
                 * 清楚用户授权数据缓存
                 */
                redisService.delete(Constant.IDENTIFY_CACHE_KEY+userId);
            }
        }
        return Response.success(ResponseCode.SUCCESS.getMessage());
    }

    /**
     * 更新角色状态
     * @param roleId
     * @param status
     * @return
     */
    @Override
    public Response<String> updateRoleStatus(String roleId, Integer status) {
        if (StringUtils.isBlank(roleId)|| ObjectUtils.isEmpty(status)){
            return Response.error(ResponseCode.DATA_ERROR.getMessage());
        }
        SysRole sysRole = new SysRole();
        sysRole.setId(roleId);
        sysRole.setStatus(status);
        int result = sysRoleDao.updateRoleInfo(sysRole);
        if (result == 0){
            return Response.error(ResponseCode.OPERATION_ERROR.getMessage());
        }
        return Response.success(ResponseCode.SUCCESS.getMessage());
    }

    /**
     * 删除角色信息
     * @param roleId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> deletedRole(String roleId) {
        // 1.将sys_role表的deleted字段置为弃用
        SysRole sysRole = new SysRole();
        sysRole.setId(roleId);
        sysRole.setDeleted(Constant.RoleStatus.DEPRECATED);
        sysRole.setUpdateTime(new Date());
        int updateCount = sysRoleDao.updateRoleInfo(sysRole);
        if (updateCount != 1){
            throw new BusinessException(ResponseCode.OPERATION_ERROR);
        }
        // 2.角色菜单权限关联数据删除，通过角色id删除权限id
        rolePermissionService.removeByRoleId(roleId);
        // 3.查询需要标记主动刷新的用户
        List<String> userIdsByRoleId = userRoleService.getUserIdsByRoleId(roleId);
        // 4.用户角色关联信息删除，通过角色id删除用户id
        userRoleService.removeUseIdsrRoleId(roleId);
        // 5.把跟该角色关联的用户标记起来，需要主动刷新token
        if (userIdsByRoleId != null && !userIdsByRoleId.isEmpty()){
            for (String userId : userIdsByRoleId){
                /**
                 * 标记用户需要重新登录,禁止再访问我们的系统资源
                 */
                redisService.set(Constant.JWT_USER_LOGIN_BLACKLIST+userId,userId);
                /**
                 * 清楚用户授权数据缓存
                 */
                redisService.delete(Constant.IDENTIFY_CACHE_KEY+userId);
            }
        }
        return Response.success(ResponseCode.SUCCESS.getMessage());
    }

    /**
     * 查询所有权限
     * @return
     */
    @Override
    public List<SysRole> queryRolePageInfo() {
        return sysRoleDao.queryRolePageInfo(new RolePageReqVo());
    }
}
