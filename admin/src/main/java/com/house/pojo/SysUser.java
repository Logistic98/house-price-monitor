package com.house.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SysUser implements Serializable {
    private String id;

    private String username;

    private String password;

    private String nickName;

    private String email;

    private Integer status;

    private Integer deleted;

    private String createId;

    private String updateId;

    private Date createTime;

    private Date updateTime;

    private String remark;

}