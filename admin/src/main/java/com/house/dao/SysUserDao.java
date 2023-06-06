package com.house.dao;

import com.house.pojo.SysUser;
import com.house.vo.req.UserPageReqVo;
import com.house.vo.resp.UserTableRespVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserDao {

    /**
     * 分页查询用户信息
     * @param userPageReqVo
     * @return
     */
    List<UserTableRespVo> queryUserPageInfo(UserPageReqVo userPageReqVo);

    /**
     * 校验用户名是否重复
     * @param username
     * @return
     */
    SysUser checkUsername(@Param("username") String username);

    /**
     * 新增用户信息
     * @param record
     * @return
     */
    int insertUserInfo(SysUser record);

    /**
     * 根据id主键查询用户信息
     * @param userId
     * @return
     */
    SysUser selectByPrimaryKey(String userId);

    /**
     * 更新用户信息
     * @param sysUser
     * @return
     */
    int updateUserInfo(SysUser sysUser);

    /**
     * 批量/删除用户
     * @param sysUser
     * @param list
     * @return
     */
    int deletedUsers(@Param("sysUser") SysUser sysUser, @Param("list") List<String> list);

}