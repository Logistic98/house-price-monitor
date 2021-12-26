#!/usr/bin/env python
# coding=utf-8
# 二手房信息的数据结构


class Rent(object):
    def __init__(self, district, area, descs, layout, size, price):
        self.district = district
        self.area = area
        self.descs = descs
        self.layout = layout
        self.size = size
        self.price = price

    def text(self):
        return self.district + "," + \
               self.area + "," + \
               self.descs + "," + \
               self.layout + "," + \
               self.size + "," + \
               self.price
