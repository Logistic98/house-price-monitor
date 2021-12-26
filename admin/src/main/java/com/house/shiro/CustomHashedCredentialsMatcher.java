package com.house.shiro;

import com.house.constant.Constant;
import com.house.enums.ResponseCode;
import com.house.exception.BusinessException;
import com.house.service.RedisService;
import com.house.utils.JwtTokenUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

/** 自定义的认证器
 *
 * Shiro 的 认证器 HashedCredentialsMatcher
 * Shiro主要是用过 HashedCredentialsMatcher 的 doCredentialsMatch方法对提交的用户名密码组装的 token 进行效验，我们这使用的是 jwt-token
 */
public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Autowired
    private RedisService redisService;

    /**
     * 真正的效验（主要是通过 HashedCredentialsMatcher 的 doCredentialsMatch 方法返回值进行效验）
     * 我们使用的是 jwt-token ，所以在此处效验
     * @param token
     * @param info
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CustomUsernamePasswordToken customUsernamePasswordToken = (CustomUsernamePasswordToken) token;
        String accessToken = (String) customUsernamePasswordToken.getPrincipal();
        String userId = JwtTokenUtil.getInstance().getUserId(accessToken);
        /**
         * 判断用户是否被锁定
         */
        if (redisService.hasKey(Constant.ACCOUNT_LOCK_KEY + userId)){
            throw new BusinessException(ResponseCode.ACCOUNT_LOCK);
        }
        /**
         * 判断用户是否被删除
         */
        if (redisService.hasKey(Constant.DELETED_USER_KEY + userId)){
            throw new BusinessException(ResponseCode.ACCOUNT_HAS_DELETED_ERROR);
        }

        /**
         * 判断用户是否在加入黑名单 禁止再访问我们的系统资源，让用户重新登录
         */
        if (redisService.hasKey(Constant.JWT_USER_LOGIN_BLACKLIST + userId)){
            throw new BusinessException(ResponseCode.TOKEN_PAST_DUE);
        }
        /**
         * 判断token是否通过校验（不通过，token 失效）
         */
        if (!JwtTokenUtil.getInstance().checkToken(accessToken)){
            throw new BusinessException(ResponseCode.TOKEN_PAST_DUE);
        }
        if (redisService.hasKey(Constant.JWT_USER_NAME+userId)){
            String userAccessToken = (String) redisService.get(Constant.JWT_USER_NAME+userId);
            if (!accessToken.equals(userAccessToken)){
                throw new BusinessException(ResponseCode.TOKEN_EXISTS);
            }
        }
        return true;
    }
}
