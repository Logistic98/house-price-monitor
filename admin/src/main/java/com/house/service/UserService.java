package com.house.service;

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

import java.util.List;

public interface UserService {

    /**
     * 登录
     * @param loginReqVo
     * @return
     */
    Response<LoginRespVo> login(LoginReqVo loginReqVo);

    /**
     * 退出登录
     * @param accessToken
     * @return
     */
    Response<String> logout(String accessToken);

    /**
     * 分页查询用户信息
     * @param userPageReqVo
     * @return
     */
    Response<PageVo<UserTableRespVo>> queryUserPageInfo(UserPageReqVo userPageReqVo);

    /**
     * 新增用户
     * @param userAddReqVo
     * @return
     */
    Response<String> addUser(UserAddReqVo userAddReqVo,String userId);

    /**
     * 查询用户拥有的角色
     * @param userId
     * @return
     */
    Response<UserOwnRoleRespVo> getUserOwnRole(String userId);

    /**
     * 保存用户拥有的角色信息
     * @param vo
     * @return
     */
    Response<String> setUserOwnRole(UserOwnRoleReqVo vo);

    /**
     * 更新用户信息
     * @param userUpdateReqVo
     * @param operationId 变更人
     * @return
     */
    Response<String> updateUserInfo(UserUpdateReqVo userUpdateReqVo, String operationId);

    /**
     * 修改个人密码
     * @param userUpdatePasswordReqVo
     * @param accessToken
     * @return
     */
    Response<String> userUpdatePassword(UserUpdatePasswordReqVo userUpdatePasswordReqVo, String accessToken);

    /**
     * 重置用户密码
     * @param userId
     * @return
     */
    Response<String> resetUpdatePassword(String userId);

    /**
     * 获取个人资料编辑信息
     * @param userId
     * @return
     */
    Response<UserRespVo> queryUserdetailInfo(String userId);

    /**
     * 保存个人信息
     * @param updateDetailInfoReqVo
     * @param userId
     * @return
     */
    Response<String> userUpdateDetailInfo(UserUpdateDetailInfoReqVo updateDetailInfoReqVo, String userId);

    /**
     * 批量/删除用户
     * @param list
     * @param operationId 操作人
     * @return
     */
    Response<String> deletedUsers(List<String> list, String operationId);

    /**
     * 用户注册
     * @param userAddReqVo
     * @return
     */
    Response<String> register(UserAddReqVo userAddReqVo);

}
