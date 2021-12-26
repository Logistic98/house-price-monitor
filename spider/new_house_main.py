#!/usr/bin/env python
# coding=utf-8
# 获得指定城市的所有新房楼盘数据

from lib.spider.new_house_spider import *

if __name__ == "__main__":
    spider = NewHouseBaseSpider(SPIDER_NAME)
    spider.start()
