package com.house.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PermissionRespNodeVo {

    @ApiModelProperty(value = "权限id")
    private String id;

    @ApiModelProperty(value = "菜单权限名称")
    private String title;

    @ApiModelProperty(value = "菜单权限图标")
    private String icon;

    @ApiModelProperty(value = "菜单地址")
    private String path;

    @ApiModelProperty(value = "菜单name")
    private String name;

    @ApiModelProperty(value = "子集集合")
    private List<PermissionRespNodeVo> children;

    /*@ApiModelProperty(value = "默认展开")
    private boolean spread=true;*/

   /* @ApiModelProperty(value = "节点是否选中")
    private boolean checked;*/
}
