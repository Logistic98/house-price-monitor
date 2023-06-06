package com.house.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "消息通知查询数据对象",description = "消息通知展示数据对象")
@Data
public class XxlNewsPageRespVo {

    @ApiModelProperty(value = "日志id")
    private String id;

    @ApiModelProperty(value = "调度时间")
    private String triggerTime;

    @ApiModelProperty(value = "调度结果")
    private String triggerCode;

    @ApiModelProperty(value = "调度日志")
    private String triggerMsg;

    @ApiModelProperty(value = "执行时间")
    private String handleTime;

    @ApiModelProperty(value = "执行结果")
    private String handleCode;

    @ApiModelProperty(value = "执行时间")
    private String handleMsg;

}
