package com.house.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RuntimeSummaryRespVo {

    @ApiModelProperty(value = "方法名")
    private String method;

    @ApiModelProperty(value = "方法描述")
    private String desc;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "时限范围")
    private String timeRange;

    @ApiModelProperty(value = "结果数据")
    private byte[] data;

    @ApiModelProperty(value = "输出的结果数据")
    private String outputData;

}
