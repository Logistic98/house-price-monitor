package com.house.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommunityReqVo {

    @ApiModelProperty(value = "当前第几页")
    private Integer pageNum=1;

    @ApiModelProperty(value = "当前页数量")
    private Integer pageSize=10;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "区县")
    private String district;

    @ApiModelProperty(value = "区域")
    private String area;

    @ApiModelProperty(value = "小区")
    private String community;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "最低价格")
    private String startPrice;

    @ApiModelProperty(value = "最高价格")
    private String endPrice;
}
