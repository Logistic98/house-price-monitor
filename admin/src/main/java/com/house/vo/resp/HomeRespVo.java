package com.house.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "首页数据对象",description = "首页展示数据对象")
@Data
public class HomeRespVo {
    @ApiModelProperty(value = "用户信息")
    private UserRespVo userInfo;

    @ApiModelProperty(value = "权限菜单")
    private List<PermissionRespNodeVo> menus;
}
