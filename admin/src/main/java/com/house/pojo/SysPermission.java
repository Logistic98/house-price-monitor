package com.house.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysPermission implements Serializable {
    private String id;

    private String code;

    private String title;

    private String icon;

    private String perms;

    private String url;

    private String method;

    private String name;

    private String pid;

    private Integer orderNum;

    private Integer type;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    private String pidName;
}