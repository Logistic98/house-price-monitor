#!/usr/bin/env python
# coding=utf-8
# 获得指定城市的小区数据

from lib.spider.community_spider import *

if __name__ == "__main__":
    spider = CommunityBaseSpider(SPIDER_NAME)
    spider.start()
