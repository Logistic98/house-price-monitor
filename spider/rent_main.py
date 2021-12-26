#!/usr/bin/env python
# coding=utf-8
# 获得指定城市的出租房数据

from lib.spider.rent_spider import *

if __name__ == "__main__":
    spider = RentBaseSpider(SPIDER_NAME)
    spider.start()
