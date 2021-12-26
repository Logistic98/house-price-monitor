package com.house.vo.req;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RolePageReqVo {

    @ApiModelProperty(value = "第几页")
    private int pageNum=1;
    @ApiModelProperty(value = "当前页的数量")
    private int pageSize=10;

    @ApiModelProperty(value = "角色id")
    private String roleId;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "角色状态")
    private Integer status;
    @ApiModelProperty(value = "开始时间")
    private String startTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
