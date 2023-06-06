package com.house.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "消息通知查询数据对象",description = "消息通知查询数据对象")
@Data
public class XxlNewsPageReqVo {

    @ApiModelProperty(value = "当前第几页")
    private Integer pageNum=1;

    @ApiModelProperty(value = "当前页数量")
    private Integer pageSize=10;

    @ApiModelProperty(value = "起始时间")
    private String startTime;

    @ApiModelProperty(value = "终止时间")
    private String endTime;

    @ApiModelProperty(value = "调度结果")
    private String triggerCode;

    @ApiModelProperty(value = "执行结果")
    private String handleCode;
}
