package com.house.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SecondHandReqVo {

    @ApiModelProperty(value = "当前第几页")
    private Integer pageNum=1;

    @ApiModelProperty(value = "当前页数量")
    private Integer pageSize=10;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "区域")
    private String district;

    @ApiModelProperty(value = "最低价格")
    private String startPrice;

    @ApiModelProperty(value = "最高价格")
    private String endPrice;

    @ApiModelProperty(value = "最小空间")
    private String startSize;

    @ApiModelProperty(value = "最大空间")
    private String endSize;

    @ApiModelProperty(value = "布局")
    private String layout;

    @ApiModelProperty(value = "朝向")
    private String direction;

    @ApiModelProperty(value = "装修")
    private String furnish;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;


}
