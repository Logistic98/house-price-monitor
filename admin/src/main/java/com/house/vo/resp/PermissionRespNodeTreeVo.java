package com.house.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PermissionRespNodeTreeVo {

    @ApiModelProperty(value = "权限id")
    private String id;

    @ApiModelProperty(value = "菜单权限名称")
    private String title;

    @ApiModelProperty(value = "level几级子菜单")
    private int level;
}
