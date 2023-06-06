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
public class XxlJobLog implements Serializable {
    private Long id;

    private Integer jobGroup;

    private Integer jobId;

    private String executorAddress;

    private String executorHandler;

    private String executorParam;

    private String executorShardingParam;

    private Integer executorFailRetryCount;

    private Date triggerTime;

    private Integer triggerCode;

    private Date handleTime;

    private Integer handleCode;

    private Byte alarmStatus;

    private String ignore;

    private String triggerMsg;

    private String handleMsg;

}