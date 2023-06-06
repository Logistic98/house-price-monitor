package com.house.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

// TODO: vue中废弃不用
@Data
public class RoleDetailInfoRespVo implements Serializable {
    @ApiModelProperty(value = "角色id")
    private String id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "角色状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "角色拥有的权限结构数据")
    private List<PermissionRespNodeVo> permissionRespNode;
}