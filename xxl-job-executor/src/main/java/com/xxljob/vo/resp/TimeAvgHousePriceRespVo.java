package com.xxljob.vo.resp;

import lombok.Data;

@Data
public class TimeAvgHousePriceRespVo {
    private String timeRange;

    private String avgPrice;

    private String city;

    private String type;

    private String date;
}
