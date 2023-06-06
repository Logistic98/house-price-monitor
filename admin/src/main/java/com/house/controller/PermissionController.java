package com.house.controller;

import com.house.aop.annotation.MyLog;
import com.house.service.PermissionService;
import com.house.utils.Response;
import com.house.vo.req.PermissionAddReqVo;
import com.house.vo.req.PermissionUpdateReqVo;
import com.house.vo.resp.PermissionRespNodeTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
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
import java.util.List;

@Api(value = "组织管理-菜单权限管理",tags = "菜单权限管理相关接口")
@RestController
@RequestMapping("/api")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @MyLog(title = "组织管理-菜单权限管理",action = "获取所有的菜单权限数据接口")
    @ApiOperation(value = "获取所有权限（包括按钮）",notes = "角色添加/编辑/分配权限时用到的结果数据")
    @RequiresPermissions(value = {"sys:role:update","sys:role:add"},logical = Logical.OR)
    @GetMapping("/permissions/tree/all")
    public Response getAllPermissionTree(){
        return Response.success(permissionService.selectAllTree());
    }

    @MyLog(title = "组织管理-菜单权限管理",action = "只递归查询到菜单结构数据接口")
    @ApiOperation(value = "菜单权限表格展示-获取所有的菜单权限数据接口",notes = "菜单权限接口")
    @RequiresPermissions("sys:permission:list")
    @GetMapping("/permissions")
    public Response getAllPermission(){
        return Response.success(permissionService.selectAll());
    }

    @MyLog(title = "组织管理-菜单权限管理",action = "新增菜单权限接口")
    @ApiOperation(value = "菜单权限树",notes = "只递归查询目录与菜单，不要按钮，添加/编辑-权限的上级选择权限树结构展示")
    @RequiresPermissions(value = {"sys:permission:update","sys:permission:add"},logical = Logical.OR)
    @GetMapping("/permissions/tree")
    public Response<List<PermissionRespNodeTreeVo>> getAllPermissionTreeExBtn(){
        return permissionService.selectAllMenuByTree();
    }

    @MyLog(title = "组织管理-菜单权限管理",action = "新增菜单权限接口")
    @ApiOperation(value = "新增权限",notes = "新增菜单权限接口")
    @RequiresPermissions("sys:permission:add")
    @PostMapping("/permission")
    public Response<String> createPermission(@RequestBody @Valid PermissionAddReqVo permissionAddReqVO){
        return permissionService.addPermission(permissionAddReqVO);
    }

    @MyLog(title = "组织管理-菜单权限管理",action = "编辑菜单权限接口")
    @ApiOperation(value = "更新权限",notes = "新更新菜单权限接口")
    @RequiresPermissions("sys:permission:update")
    @PutMapping("/permission")
    public Response<String> updatePermission(@RequestBody @Valid PermissionUpdateReqVo permissionUpdateReqVo){
        return permissionService.updatePermission(permissionUpdateReqVo);
    }

    @MyLog(title = "组织管理-菜单权限管理",action = "删除菜单权限接口")
    @ApiOperation(value = "删除权限",notes = "删除菜单权限接口")
    @RequiresPermissions("sys:permission:delete")
    @DeleteMapping("/permission/{permissionId}")
    public Response<String> deletedPermission(@PathVariable("permissionId") String permissionId){
        return permissionService.deletedPermission(permissionId);
    }
}
