package com.house.controller;

import com.house.aop.annotation.MyLog;
import com.house.constant.Constant;
import com.house.service.UserService;
import com.house.utils.JwtTokenUtil;
import com.house.utils.Response;
import com.house.vo.req.LoginReqVo;
import com.house.vo.req.UserAddReqVo;
import com.house.vo.req.UserOwnRoleReqVo;
import com.house.vo.req.UserPageReqVo;
import com.house.vo.req.UserUpdateDetailInfoReqVo;
import com.house.vo.req.UserUpdatePasswordReqVo;
import com.house.vo.req.UserUpdateReqVo;
import com.house.vo.resp.LoginRespVo;
import com.house.vo.resp.PageVo;
import com.house.vo.resp.UserOwnRoleRespVo;
import com.house.vo.resp.UserRespVo;
import com.house.vo.resp.UserTableRespVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Api(value = "用户登录/登出",tags = "用户登录/登出相关的接口")
@RestController
@RequestMapping("/api")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param loginReqVo
     * @return
     */
    @ApiOperation(value = "用户登录",notes = "用户登录接口")
    @PostMapping("/login")
    public Response<LoginRespVo> login(@RequestBody @Valid LoginReqVo loginReqVo){
        return userService.login(loginReqVo);
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @ApiOperation(value = "用户登出",notes = "用户登出接口")
    @GetMapping("/logout")
    public Response<String> logout(HttpServletRequest request){
        String accessToken = null;
        try {
            accessToken = request.getHeader(Constant.ACCESS_TOKEN);
            return userService.logout(accessToken);
        } catch (Exception e) {
            logger.error("【logout error】，{}",e);
        }
        return Response.success();
    }

    @MyLog(title = "组织管理-用户管理",action = "分页查询用户接口")
    @ApiOperation(value = "分页查询信息",notes = "分页查询用户接口")
    @RequiresPermissions("sys:user:list")
    @PostMapping("/users")
    public Response<PageVo<UserTableRespVo>> queryUserPageInfo(@RequestBody @Valid UserPageReqVo userPageReqVo){
        return userService.queryUserPageInfo(userPageReqVo);
    }

    @MyLog(title = "组织管理-用户管理",action = "新增用户接口")
    @ApiOperation(value = "新增用户",notes = "新增用户接口")
    @RequiresPermissions("sys:user:add")
    @PostMapping("/user")
    public Response<String> addUser(@RequestBody @Valid UserAddReqVo userAddReqVo, HttpServletRequest request){
        String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
        String userId = JwtTokenUtil.getInstance().getUserId(accessToken); // 创建人
        return userService.addUser(userAddReqVo, userId);
    }

    @MyLog(title = "组织管理-用户管理",action = "查询用户拥有的角色接口")
    @ApiOperation(value = "查询用户拥有的角色",notes = "查询用户拥有的角色接口")
    @GetMapping("/user/roles/{userId}")
    public Response<UserOwnRoleRespVo> getUserOwnRole(@PathVariable("userId")String userId){
        return userService.getUserOwnRole(userId);
    }

    @MyLog(title = "组织管理-用户管理",action = "保存用户拥有的角色信息接口")
    @ApiOperation(value = "保存用户拥有的角色信息",notes = "保存用户拥有的角色信息接口")
    @RequiresPermissions("sys:user:role:update")
    @PutMapping("/user/roles")
    public Response<String> saveUserOwnRole(@RequestBody @Valid UserOwnRoleReqVo vo){
        return userService.setUserOwnRole(vo);
    }

    @MyLog(title = "组织管理-用户管理",action = "更新用户信息接口")
    @ApiOperation(value = "更新用户信息",notes = "更新用户信息接口")
    @RequiresPermissions("sys:user:update")
    @PutMapping("/user")
    public Response<String> updateUserInfo(@RequestBody @Valid UserUpdateReqVo userUpdateReqVo,HttpServletRequest request){
        String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
        String operationId = JwtTokenUtil.getInstance().getUserId(accessToken); // 变更人
        return userService.updateUserInfo(userUpdateReqVo,operationId);
    }

    @MyLog(title = "组织管理-用户管理",action = "修改个人密码接口")
    @ApiOperation(value = "修改个人密码",notes = "修改个人密码接口")
    @PutMapping("/user/password")
    public Response<String> updatePassword(@RequestBody @Valid UserUpdatePasswordReqVo userUpdatePasswordReqVo, HttpServletRequest request){
        String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
        return userService.userUpdatePassword(userUpdatePasswordReqVo,accessToken);
    }

    @MyLog(title = "组织管理-重置用户密码",action = "重置用户密码接口")
    @ApiOperation(value = "重置用户密码",notes = "重置用户密码接口")
    @RequiresPermissions("sys:user:role:update")
    @GetMapping("/user/password/{id}")
    public Response<String> resetUpdatePassword(@PathVariable("id") String userId){
        return userService.resetUpdatePassword(userId);
    }

    @MyLog(title = "组织管理-用户管理",action = "用户信息详情接口")
    @ApiOperation(value = "用户信息详情",notes = "用户信息详情接口")
    @GetMapping("/user/info")
    public Response<UserRespVo> queryUserdetailInfo(HttpServletRequest request){
        String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
        String userId = JwtTokenUtil.getInstance().getUserId(accessToken);
        return userService.queryUserdetailInfo(userId);
    }

    @MyLog(title = "组织管理-用户管理",action = "保存个人信息接口")
    @ApiOperation(value = "保存个人信息",notes = "保存个人信息接口")
    @PutMapping("/user/info")
    public Response<String> saveUserInfo(@RequestBody UserUpdateDetailInfoReqVo updateDetailInfoReqVo, HttpServletRequest request){
        String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
        String userId = JwtTokenUtil.getInstance().getUserId(accessToken);
        return userService.userUpdateDetailInfo(updateDetailInfoReqVo,userId);
    }

    @MyLog(title = "组织管理-用户管理",action = "批量/删除用户接口")
    @ApiOperation(value = "批量/删除用户",notes = "批量/删除用户接口")
    @RequiresPermissions("sys:user:delete")
    @DeleteMapping("/user")
    public Response<String> deletedUsers(@RequestBody @ApiParam(value = "用户id集合")List<String> list,HttpServletRequest request){
        String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
        String operationId = JwtTokenUtil.getInstance().getUserId(accessToken);// 变更人
        return userService.deletedUsers(list,operationId);
    }

    @ApiOperation(value = "用户注册",notes = "用户注册接口")
    @PostMapping("/register")
    public Response<String> register(@RequestBody @Valid UserAddReqVo userAddReqVo){
        return userService.register(userAddReqVo);
    }

}