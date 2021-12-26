package com.house.enums;

/**
 * 响应码
 */
public enum ResponseCode implements BaseCodeEnum {
    /**
     * 这个要和前端约定好
     *code=1：服务器已成功处理了请求。 通常，这表示服务器提供了请求的网页。
     *code=2：（授权异常） 请求要求身份验证。 客户端需要跳转到登录页面重新登录
     *code=3： 无权限访问资源
     *code=-1：(凭证过期) 客户端请求刷新凭证接口
     *code=0：表示请求失败，只提示，不跳转，2 是跳转
     *
     */
    SUCCESS(1,"操作成功"),
    SYSTEM_ERROR(0,"系统异常请稍后再试"),
    DATA_ERROR(0,"参数异常"),
    METHOD_IDENTITY_ERROR(0,"数据校验异常"),
    ACCOUNT_ERROR(0,"该账号不存在"),
    ACCOUNT_EXISTS_ERROR(0,"该账号已存在"),
    ACCOUNT_LOCK(0,"该账号被锁定,请联系系统管理员"),
    ACCOUNT_PASSWORD_ERROR(0,"用户名密码不匹配"),
    TOKEN_ERROR(2,"用户未登录，请重新登录"),
    TOKEN_NOT_NULL(-1,"token 不能为空"),
    SHIRO_AUTHENTICATION_ERROR(0,"用户认证异常"),
    ACCOUNT_HAS_DELETED_ERROR(2,"该账号已被删除，请联系系统管理员"),
    TOKEN_PAST_DUE(-1,"登录失效,请重新登录"),
    TOKEN_EXISTS(-1,"账号异地登录，你已被迫退出"),
    NOT_PERMISSION(3,"没有权限访问该资源"),
    OPERATION_ERROR(0,"操作失败"),
    OPERATION_MENU_PERMISSION_CATALOG_ERROR(0,"操作后的菜单类型是目录，所属菜单必须为默认顶级菜单或者目录"),
    OPERATION_MENU_PERMISSION_MENU_ERROR(0,"操作后的菜单类型是菜单，所属菜单必须为目录类型"),
    OPERATION_MENU_PERMISSION_BTN_ERROR(0,"操作后的菜单类型是按钮，所属菜单必须为菜单类型"),
    OPERATION_MENU_PERMISSION_URL_NOT_NULL(0,"菜单权限的url不能为空"),
    OPERATION_MENU_PERMISSION_URL_PERMS_NULL(0,"菜单权限的标识符不能为空"),
    OPERATION_MENU_PERMISSION_URL_METHOD_NULL(0,"菜单权限的请求方式不能为空"),
    ACCOUNT_LOCK_TIP(2,"该账号被锁定,请联系系统管理员"),
    OPERATION_MENU_PERMISSION_UPDATE(0,"操作的菜单权限存在子集关联不允许变更"),
    ROLE_PERMISSION_RELATION(0, "该菜单权限存在子集关联，不允许删除"),
    NOT_PERMISSION_DELETED_DEPT(0,"该组织机构下还关联着用户，不允许删除"),
    OLD_PASSWORD_ERROR(0,"旧密码不匹配"),
    OPERATION_MENU_PERMISSION_URL_CODE_NULL(0,"菜单权限的按钮标识不能为空"),
    ;

    private int code;
    private String message;

    ResponseCode(int code,String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
