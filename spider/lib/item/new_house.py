#!/usr/bin/env python
# coding=utf-8
# 新房楼盘的数据结构


class NewHouse(object):
    def __init__(self, property, price, total):
        self.property = property
        self.price = price
        self.total = total

    def text(self):
        return self.property + "," + \
               self.price + "," + \
               self.total
