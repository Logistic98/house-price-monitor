package com.house.constant;

/**
 * 系统常量
 */
public abstract class Constant {

    public static String JWT_USER_NAME="jwt-user-name-key"; // 用户名称 key（用于生成token与只能一个账号同时存在）
    public static String JWT_PERMISSIONS_KEY="jwt-permissions-key_"; // 权限key
    public static String JWT_ROLES_KEY="jwt-roles-key_"; // 角色key
    public static String JWT_USER_LOGIN_BLACKLIST="jwt-access-token-blacklist_"; // 标记用户需要重新登录，场景 比如修改了用户的角色/权限/菜单
    public static String ACCESS_TOKEN="authorization"; // 正常token
    public static String ACCOUNT_LOCK_KEY="account-lock-key_"; // 标记用户是否已经被锁定
    public static String DELETED_USER_KEY="deleted-user-key_"; // 标记用户是否已经删除
    public static String IDENTIFY_CACHE_KEY="shiro-cache:com.xh.lesson.shiro.CustomRealm.authorizationCache:"; // 用户权鉴缓存key

    public static String DEFAULT_PASSWORD="123456"; // 重置密码
    public static String REGISTER_USER_ROLE="1237258113002901513"; // 注册的用户角色（普通用户）

    // 是否删除
    public final static class Deleted{
        public static int DELETED = 0;  // 已删除
        public static int NOT_DELETED = 1;  // 未删除
    }

    // 账户状态
    public final static class UserStatus{
        public static int NORMAL = 1;  // 正常
        public static int LOCK = 2;  // 锁定
    }

    // 角色状态
    public final static class RoleStatus{
        public static int DEPRECATED = 0;  // 弃用
        public static int NORMAL = 1;  // 正常
    }

    // 权限状态
    public final static class PermissionStatus{
        public static int DEPRECATED = 0;  // 弃用
        public static int NORMAL = 1;  // 正常
    }

    // 权限类型
    public final static class PermissionType{
        public static int CONTENT = 1;  // 目录
        public static int MENU = 2;  // 菜单
        public static int BUTTON = 3;  // 按钮
    }

}
