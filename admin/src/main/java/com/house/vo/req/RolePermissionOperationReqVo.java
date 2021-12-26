package com.house.vo.req;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class RolePermissionOperationReqVo {

    @ApiModelProperty(value = "角色id")
    @NotBlank(message = "角色id不能为空")
    private String roleId;

    @ApiModelProperty(value = "菜单权限集合id")
    @NotBlank(message = "菜单权限集合不能为空")
    private List<String> permissionIds;
}
