#!/usr/bin/env python
# coding=utf-8
# 获得指定城市的二手房数据

from lib.spider.second_hand_spider import *

if __name__ == "__main__":
    spider = SecondHandSpider(SPIDER_NAME)
    spider.start()
