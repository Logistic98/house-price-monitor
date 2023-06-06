package com.house.shiro;

import com.house.dao.SysUserRoleDao;
import com.house.service.RedisService;
import com.house.utils.JwtTokenUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/** 自定义域 CustomRealm
 *
 * Realm即领域，相当于datasource数据源，securityManager进行安全认证需要通过Realm获取用户身份信息及用户权限数据，
 * 比如：如果用户身份数据在数据库那么realm就需要从数据库获取用户身份信息
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private RedisService redisService;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    /**
     * 此方法必须有，不然我们自定义的 CustomUsernamePasswordToken 不生效
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof CustomUsernamePasswordToken;
    }

    /**
     * 用户授权，设置用户所拥有的 角色/权限
     * @param principals
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String accessToken = (String) principals.getPrimaryPrincipal();
        // 授权器
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        JwtTokenUtil instance = JwtTokenUtil.getInstance();
        String userId = instance.getUserId(accessToken);
        // 判断 redis 中是否缓存有权限信息
        //if (redisService.hasKey(Constant.IDENTIFY_CACHE_KEY + userId)){
        //    redisService.get(Constant.IDENTIFY_CACHE_KEY + userId);
        //}
        // 通过用户id获取该用户所拥有的角色名称
        List<String> roleNames = sysUserRoleDao.getRoleNameByUserId(userId);
        if (roleNames != null && !roleNames.isEmpty()){
            authorizationInfo.addRoles(roleNames);
        }
        // 通过用户id获取该用户所拥有的权限授权 如：sys:user:add
        List<String> permissionPerms = sysUserRoleDao.getPermissionPermsByUserId(userId);
        if (permissionPerms != null && !permissionPerms.isEmpty()){
            authorizationInfo.addStringPermissions(permissionPerms);
        }
        return authorizationInfo;
    }

    /**
     * 用户认证，以前是验证用户名/密码。现在我们验证 token，吧我们的 token 交还给 认证器
     * @param token
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 把我们的 token 交还给 认证器
        CustomUsernamePasswordToken customUsernamePasswordToken = (CustomUsernamePasswordToken) token;
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(customUsernamePasswordToken.getPrincipal(), customUsernamePasswordToken.getCredentials(), CustomRealm.class.getName());
        return info;
    }
}
