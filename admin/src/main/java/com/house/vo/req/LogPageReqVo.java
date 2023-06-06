package com.house.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LogPageReqVo {

    @ApiModelProperty("当前页数")
    private Integer pageNum=1;

    @ApiModelProperty(value = "当前页的数量")
    private int pageSize=10;

    @ApiModelProperty(value = "用户操作动作")
    private String operation;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
