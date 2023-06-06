package com.house.vo.resp;

import com.house.pojo.SysRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserOwnRoleRespVo {

    @ApiModelProperty(value = "用户拥有的权限ids")
    private List<String> ownRoleIds;

    @ApiModelProperty(value = "所有权限")
    private List<SysRole> allRole;
}
