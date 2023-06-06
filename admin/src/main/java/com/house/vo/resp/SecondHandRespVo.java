package com.house.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "二手房查询数据对象",description = "二手房查询展示数据对象")
@Data
public class SecondHandRespVo {
    @ApiModelProperty(value = "当前第几页")
    private Integer pageNum=1;

    @ApiModelProperty(value = "当前页数量")
    private Integer pageSize=10;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "区域")
    private String area;

    @ApiModelProperty(value = "房源描述")
    private String descs;

    @ApiModelProperty(value = "布局")
    private String layout;

    @ApiModelProperty(value = "大小")
    private String size;

    @ApiModelProperty(value = "朝向")
    private String direction;

    @ApiModelProperty(value = "装修")
    private String furnish;

    @ApiModelProperty(value = "楼层")
    private String floor;

    @ApiModelProperty(value = "房屋类型")
    private String type;

    @ApiModelProperty(value = "价格")
    private String price;

    @ApiModelProperty(value = "区县")
    private String district;

    @ApiModelProperty(value = "时间")
    private String time;

    @ApiModelProperty(value = "数据来源")
    private String source;
}
