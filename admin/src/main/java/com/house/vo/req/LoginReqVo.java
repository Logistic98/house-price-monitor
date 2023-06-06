package com.house.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "登录对象",description = "用户登录的参数对象")
public class LoginReqVo {

    @ApiModelProperty(value = "账号")
    @NotBlank(message = "账号不能为空")
    @Length(max = 20,message = "账号名称长度需要在20个字符以内")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

}
