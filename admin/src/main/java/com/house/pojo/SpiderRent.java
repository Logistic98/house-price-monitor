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
public class SpiderRent implements Serializable {

    private String city;

    private String area;

    private String descs;

    private String layout;

    private String size;

    private String price;

    private String district;

    private String time;

    private String source;

}