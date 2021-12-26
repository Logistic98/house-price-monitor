package com.house.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpiderRuntimeSummary implements Serializable {

    private Long id;

    private String method;

    private String desc;

    private String city;

    private String updateTime;

    private String type;

    private String timeRange;

    private byte[] data;
}