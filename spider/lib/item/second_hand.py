#!/usr/bin/env python
# coding=utf-8
# 二手房信息的数据结构


class SecondHand(object):
    def __init__(self, district, area, name, price, desc):
        self.district = district
        self.area = area
        self.price = price
        self.name = name
        self.desc = desc

    def text(self):
        return self.district + "," + \
               self.area + "," + \
               self.name + "," + \
               self.price + "," + \
               self.desc
