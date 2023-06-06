package com.house.controller;

import com.house.aop.annotation.MyLog;
import com.house.service.RoleService;
import com.house.utils.Response;
import com.house.vo.req.RolePageReqVo;
import com.house.vo.req.RoleReqVo;
import com.house.vo.req.RoleUpdateReqVo;
import com.house.vo.resp.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@Api(value = "组织管理-角色管理",tags = "角色管理相关接口")
@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @MyLog(title = "组织管理-角色管理",action = "分页获取角色数据")
    @ApiOperation(value = "分页获取角色数据",notes = "分页获取角色数据接口")
    @RequiresPermissions("sys:role:list")
    @PostMapping("/roles")
    public Response<PageVo> pageInfo(@RequestBody RolePageReqVo rolePageReqVo){
        return roleService.pageInfoRoles(rolePageReqVo);
    }

    @MyLog(title = "组织管理-角色管理",action = "新增角色接口")
    @ApiOperation(value = "新增角色",notes = "新增角色接口")
    @RequiresPermissions("sys:role:add")
    @PostMapping("/role")
    public Response<String> createRole(@RequestBody @Valid RoleReqVo roleReqVo){
        return roleService.createRole(roleReqVo);
    }

    @MyLog(title = "组织管理-角色管理",action = "获取角色详情接口")
    @ApiOperation(value = "获取角色详情",notes = "获取角色详情接口")
    @RequiresPermissions("sys:role:detail")
    @GetMapping("/role/{id}")
    public Response<Set<String>> detailInfo(@PathVariable("id") String roleId){
        return roleService.detailInfo(roleId);
    }

    @MyLog(title = "组织管理-角色管理",action = "更新角色信息接口")
    @ApiOperation(value = "更新角色信息",notes = "更新角色信息接口")
    @RequiresPermissions("sys:role:update")
    @PutMapping("/role")
    public Response<String> updateRole(@RequestBody @Valid RoleUpdateReqVo roleUpdateReqVo){
        return roleService.updateRole(roleUpdateReqVo);
    }

    @MyLog(title = "组织管理-角色管理",action = "更新角色状态接口")
    @ApiOperation(value = "更新角色状态",notes = "更新角色状态接口")
    @RequiresPermissions("sys:role:update:status")
    @PostMapping("/role/{id}/{status}")
    public Response<String> updateRoleStatus(@PathVariable("id")String roleId,@PathVariable("status") Integer status){
        return roleService.updateRoleStatus(roleId,status);
    }

    @MyLog(title = "组织管理-角色管理",action = "删除角色接口")
    @ApiOperation(value = "删除角色信息",notes = "删除角色接口")
    @RequiresPermissions("sys:role:delete")
    @DeleteMapping("/role/{id}")
    public Response<String> deletedRole(@PathVariable("id") String roleId){
        return roleService.deletedRole(roleId);
    }
}
