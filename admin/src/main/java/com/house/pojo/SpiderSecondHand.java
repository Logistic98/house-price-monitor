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
public class SpiderSecondHand implements Serializable {

    private String city;

    private String area;

    private String descs;

    private String layout;

    private String size;

    private String direction;

    private String furnish;

    private String floor;

    private String type;

    private String price;

    private String district;

    private String time;

    private String source;

}