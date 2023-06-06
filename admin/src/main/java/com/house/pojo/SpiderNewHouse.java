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
public class SpiderNewHouse implements Serializable {

    private String city;

    private String property;

    private String price;

    private String total;

    private String time;

    private String source;
}