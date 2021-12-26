#!/usr/bin/env python
# coding=utf-8
# 爬取楼盘数据的爬虫派生类

import re
import math

import pymysql
from bs4 import BeautifulSoup
from lib.item.new_house import *
from lib.spider.base_spider import *
from lib.request.headers import *
from lib.utility.date import *
from lib.utility.log import logger
from lib.zone.city import get_city
from lib.zone.clear_data import clear_data
from lib.zone.get_response import get_response


class NewHouseBaseSpider(BaseSpider):
    def collect_city_new_house_data(self, city_name):

        # 获取数据
        new_houses = self.get_new_house_info(city_name)
        for new_house in new_houses:

            # 清洗数据
            text = new_house.text().strip()
            try:
                # 如果楼盘名里面没有逗号，那么总共是3项
                if text.count(',') == 2:
                    property, price, total = text.split(',')
                elif text.count(',') < 2:
                    continue
                else:
                    fields = text.split(',')
                    property = ','.join(fields[0:-2])
                    price = fields[-2]
                    total = fields[-1]
            except Exception as e:
                print(text)
                print(e)
                continue

            print("{0} {1} {2}".format(property, price, total))

            time = get_time_string()
            source = SPIDER_NAME

            # 打开数据库连接
            db = pymysql.connect(host=HOST, user=USER, passwd=PASSWD, port=PORT, db=DB, charset=CHARSET)
            # 使用cursor()方法获取操作游标
            cursor = db.cursor()
            # sql语句
            sql = "INSERT INTO spider_new_house\
            (city, property, price, total, time, source)\
            VALUES ('%s', '%s', '%s','%s','%s','%s')" \
                  % (city_name, property, price, total, time, source)

            try:
                # 打印实际执行的sql，调试使用
                # print("-----execute------" + sql)
                # 执行sql语句
                cursor.execute(sql)
                # 提交到数据库执行
                db.commit()
            except Exception:
                # 出现错误时回滚
                logger.error("Database operation found exception: {0}, Already rolled back.".format(Exception))
                db.rollback()
            # 关闭数据库连接
            db.close()


    @staticmethod
    def get_new_house_info(city_name):
        """
        爬取页面获取城市新房楼盘信息
        :param city_name: 城市
        :return: 新房楼盘信息列表
        """
        total_page = 1
        new_house_list = list()
        page = 'https://{0}.fang.{1}.com/loupan/'.format(city_name, SPIDER_NAME)
        print(page)

        response = get_response(page)
        html = response.content
        soup = BeautifulSoup(html, "lxml")

        # 获得总的页数
        try:
            page_box = soup.find_all('div', class_='page-box')[0]
            matches = re.search('.*data-total-count="(\d+)".*', str(page_box))
            total_page = int(math.ceil(int(matches.group(1)) / 10))
        except Exception as e:
            print("\tWarning: only find one page for {0}".format(city_name))
            print(e)

        print(total_page)

        # 从第一页开始,一直遍历到最后一页
        for i in range(1, total_page + 1):
            page = 'https://{0}.fang.{1}.com/loupan/pg{2}'.format(city_name, SPIDER_NAME, i)
            print(page)
            BaseSpider.random_delay()
            response = get_response(page)
            html = response.content
            soup = BeautifulSoup(html, "lxml")

            # 获得有小区信息的panel
            house_elements = soup.find_all('li', class_="resblock-list")
            for house_elem in house_elements:
                price = house_elem.find('span', class_="number")
                total = house_elem.find('div', class_="second")
                property = house_elem.find('a', class_='name')

                # 继续清理数据
                try:
                    price = price.text.strip()
                except Exception as e:
                    price = '0'
                if price == '价格待定':
                    price = '0'
                property = property.text.replace("\n", "")
                try:
                    total = total.text.strip().replace("总价", "").replace("(万/套)", "")
                except Exception as e:
                    total = '0'

                # 作为对象保存
                new_house = NewHouse(property, price, total)
                new_house_list.append(new_house)
        return new_house_list

    def start(self):
        city = get_city()
        type = "spider_new_house"
        clear_data(city, type)

        t1 = time.time()  # 开始计时
        self.collect_city_new_house_data(city)
        t2 = time.time()  # 计时结束，统计结果

        logger.info("new house data crawling completed.")
        logger.info("Total crawl {0} new house.".format(self.total_num))
        logger.info("Total cost {0} second ".format(t2 - t1))


if __name__ == '__main__':
    # 请调用根目录new_house_main.py
    pass
