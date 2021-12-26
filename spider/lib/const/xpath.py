#!/usr/bin/env python
# coding=utf-8

from lib.spider.base_spider import SPIDER_NAME, LIANJIA_SPIDER, BEIKE_SPIDER

if SPIDER_NAME == LIANJIA_SPIDER:
    ERSHOUFANG_QU_XPATH = '//*[@id="filter-options"]/dl[1]/dd/div/a'
    ERSHOUFANG_BANKUAI_XPATH = '//*[@id="filter-options"]/dl[1]/dd/div[2]/a'
    XIAOQU_QU_XPATH = '//*[@id="filter-options"]/dl[1]/dd/div/a'
    XIAOQU_BANKUAI_XPATH = '//*[@id="filter-options"]/dl[1]/dd/div[2]/a'
    DISTRICT_AREA_XPATH = '//div[3]/div[1]/dl[2]/dd/div/div[2]/a'
    CITY_DISTRICT_XPATH = '///div[3]/div[1]/dl[2]/dd/div/div/a'
elif SPIDER_NAME == BEIKE_SPIDER:
    ERSHOUFANG_QU_XPATH = '//*[@id="filter-options"]/dl[1]/dd/div/a'
    ERSHOUFANG_BANKUAI_XPATH = '//*[@id="filter-options"]/dl[1]/dd/div[2]/a'
    XIAOQU_QU_XPATH = '//*[@id="filter-options"]/dl[1]/dd/div/a'
    XIAOQU_BANKUAI_XPATH = '//*[@id="filter-options"]/dl[1]/dd/div[2]/a'
    DISTRICT_AREA_XPATH = '//div[3]/div[1]/dl[2]/dd/div/div[2]/a'
    CITY_DISTRICT_XPATH = '///div[3]/div[1]/dl[2]/dd/div/div/a'
