package com.house.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NewHouseReqVo {

    @ApiModelProperty(value = "当前第几页")
    private Integer pageNum=1;

    @ApiModelProperty(value = "当前页数量")
    private Integer pageSize=10;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "区域")
    private String property;

    @ApiModelProperty(value = "最低单价")
    private String startPrice;

    @ApiModelProperty(value = "最高单价")
    private String endPrice;

    @ApiModelProperty(value = "最低总价")
    private String startTotal;

    @ApiModelProperty(value = "最高总价")
    private String endTotal;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

}
