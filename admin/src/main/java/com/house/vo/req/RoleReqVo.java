package com.house.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class RoleReqVo {

    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    @Length(min = 2,max = 15,message = "角色名称长度为2 ~ 15个字符")
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "角色状态：1:正常0:弃用")
    private Integer status;

    @ApiModelProperty(value = "选择的菜单id集合")
    private List<String> permissionsIds;
}
