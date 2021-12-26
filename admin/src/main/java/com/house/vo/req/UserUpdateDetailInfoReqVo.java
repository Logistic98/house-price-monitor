package com.house.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户编辑对象",description = "用户编辑信息对象")
@Data
public class UserUpdateDetailInfoReqVo {

    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "账户状态(1.正常 2.锁定 )")
    private Integer status;
}
