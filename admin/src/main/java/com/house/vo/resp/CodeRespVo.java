package com.house.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "系统字典数据对象",description = "查询系统字典数据对象")
@Data
public class CodeRespVo {
    @ApiModelProperty(value = "代码编号")
    private String code;

    @ApiModelProperty(value = "代码编号说明")
    private String desc;

    @ApiModelProperty(value = "代码名称")
    private String name;

    @ApiModelProperty(value = "代码值")
    private String value;

    @ApiModelProperty(value = "序号")
    private Integer number;
}
