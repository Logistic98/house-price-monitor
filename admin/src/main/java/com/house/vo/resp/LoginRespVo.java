package com.house.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class LoginRespVo {

    @ApiModelProperty(value = "正常的业务token")
    private String accessToken;

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "权限菜单集合")
    private List<PermissionRespNodeVo> menus;

    @ApiModelProperty(value = "按钮权限集合")
    private List<String> permissions;
}
