package com.house.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RuntimeSummaryReqVo {

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "方法名")
    private String method;

    @ApiModelProperty(value = "时限范围")
    private String timeRange;
}
