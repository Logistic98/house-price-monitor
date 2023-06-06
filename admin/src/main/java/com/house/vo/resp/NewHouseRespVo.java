package com.house.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "新楼盘查询数据对象",description = "新楼盘查询展示数据对象")
@Data
public class NewHouseRespVo {
    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "楼盘名称")
    private String property;

    @ApiModelProperty(value = "单价")
    private String price;

    @ApiModelProperty(value = "总价")
    private String total;

    @ApiModelProperty(value = "时间")
    private String time;

    @ApiModelProperty(value = "数据来源")
    private String source;
}
