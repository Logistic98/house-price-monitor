package com.house.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "小区查询数据对象",description = "小区查询展示数据对象")
@Data
public class CommunityRespVo {
    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "区县")
    private String district;

    @ApiModelProperty(value = "区域")
    private String area;

    @ApiModelProperty(value = "小区")
    private String community;

    @ApiModelProperty(value = "价格")
    private String price;

    @ApiModelProperty(value = "在售房源数量")
    private String sale;

    @ApiModelProperty(value = "时间")
    private String time;

    @ApiModelProperty(value = "数据来源")
    private String source;
}
