package com.house.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "租房查询数据对象",description = "租房查询展示数据对象")
@Data
public class RentRespVo {
    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "区域")
    private String area;

    @ApiModelProperty(value = "描述")
    private String descs;

    @ApiModelProperty(value = "布局")
    private String layout;

    @ApiModelProperty(value = "大小")
    private String size;

    @ApiModelProperty(value = "价格")
    private String price;

    @ApiModelProperty(value = "区县")
    private String district;

    @ApiModelProperty(value = "时间")
    private String time;

    @ApiModelProperty(value = "数据来源")
    private String source;
}
