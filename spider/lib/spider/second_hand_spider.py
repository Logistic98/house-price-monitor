#!/usr/bin/env python
# coding=utf-8
# 爬取二手房数据的爬虫派生类

import re

import pymysql
import threadpool
from bs4 import BeautifulSoup
from lib.item.second_hand import *
from lib.utility.log import logger
from lib.zone.city import get_city
from lib.spider.base_spider import *
from lib.utility.date import *
from lib.zone.area import *
from lib.zone.clear_data import clear_data


class SecondHandSpider(BaseSpider):

    def collect_area_second_hand_data(self, city_name, area_name):

        # 获取数据
        second_hands = self.get_area_second_hand_info(city_name, area_name)
        for second_hand in second_hands:

            # 清洗数据
            text = second_hand.text().strip()
            try:
                # 如果房源描述里面没有逗号，那么总共是5项
                if text.count(',') == 4:
                    district, area, descs, price, info = text.split(',')
                elif text.count(',') < 4:
                    continue
                else:
                    fields = text.split(',')
                    district = fields[0]
                    area = fields[1]
                    descs = ','.join(fields[2:-2])
                    price = fields[-2]
                    info = fields[-1]
            except Exception as e:
                print(text)
                print(e)
                continue

            print("{0} {1} {2} {3} {4}".format(district, area, descs, price, info))

            # 对info再单独拆分
            info = info + '|'
            try:
                # 将info拆分为布局、大小、朝向、装修、楼层、房屋类型6项
                if info.count('|') == 5:
                    layout, size, direction, furnish, floor, type = info.split('|')
                    size = size.replace("平米", "")
                elif info.count('|') < 5:
                    continue
                else:
                    infoFields = info.split('|')
                    layout = infoFields[0]
                    size = infoFields[1].replace("平米", "")
                    direction = infoFields[2]
                    furnish = infoFields[3]
                    floor = infoFields[4]
                    type = '|'.join(infoFields[5:-1])
            except Exception as e:
                print(info)
                print(e)
                continue

            time = get_time_string()
            source = SPIDER_NAME

            # 打开数据库连接
            db = pymysql.connect(host=HOST, user=USER, passwd=PASSWD, port=PORT, db=DB, charset=CHARSET)
            # 使用cursor()方法获取操作游标
            cursor = db.cursor()
            # sql语句
            sql = "INSERT INTO spider_second_hand\
            (city, district, area, descs, price, layout, size, direction, furnish, floor, type, time, source)\
            VALUES ('%s', '%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')" \
                  % (city_name, district, area, descs, price, layout, size, direction, furnish, floor, type, time, source)

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
    def get_area_second_hand_info(city_name, area_name):
        """
        通过爬取页面获得城市指定版块的二手房信息
        :param city_name: 城市
        :param area_name: 版块
        :return: 二手房数据列表
        """
        total_page = 1
        district_name = area_dict.get(area_name, "")
        # 中文区县
        chinese_district = get_chinese_district(district_name)
        # 中文版块
        chinese_area = chinese_area_dict.get(area_name, "")

        second_hand_list = list()
        page = 'https://{0}.{1}.com/ershoufang/{2}/'.format(city_name, SPIDER_NAME, area_name)
        print(page)  # 打印版块页面地址

        response = get_response(page)
        html = response.content
        soup = BeautifulSoup(html, "lxml")

        # 获得总的页数，通过查找总页码的元素信息
        try:
            page_box = soup.find_all('div', class_='page-box')[0]
            matches = re.search('.*"totalPage":(\d+),.*', str(page_box))
            total_page = int(matches.group(1))
        except Exception as e:
            print("\tWarning: only find one page for {0}".format(area_name))
            print(e)

        # 从第一页开始,一直遍历到最后一页
        for num in range(1, total_page + 1):
            page = 'https://{0}.{1}.com/ershoufang/{2}/pg{3}'.format(city_name, SPIDER_NAME, area_name, num)
            print(page)  # 打印每一页的地址
            BaseSpider.random_delay()
            response = get_response(page)
            html = response.content
            soup = BeautifulSoup(html, "lxml")

            # 获得有小区信息的panel
            house_elements = soup.find_all('li', class_="clear")
            for house_elem in house_elements:
                price = house_elem.find('div', class_="totalPrice")
                name = house_elem.find('div', class_='title')
                desc = house_elem.find('div', class_="houseInfo")

                # 继续清理数据
                price = price.text.replace("\n", "").replace(" ", "").replace("万", "")
                name = name.text.replace("\n", "").replace(" ", "")
                desc = desc.text.replace("\n", "").replace(" ", "")

                # 作为对象保存
                second_hand = SecondHand(chinese_district, chinese_area, name, price, desc)
                second_hand_list.append(second_hand)
        return second_hand_list

    def start(self):
        city = get_city()
        type = "spider_second_hand"
        clear_data(city, type)
        t1 = time.time()  # 开始计时

        # 获得城市有多少区列表, district: 区县
        districts = get_districts(city)
        print('City: {0}'.format(city))
        print('Districts: {0}'.format(districts))

        # 获得每个区的板块, area: 板块
        areas = list()
        for district in districts:
            areas_of_district = get_areas(city, district)
            print('{0}: Area list:  {1}'.format(district, areas_of_district))
            # 用list的extend方法,L1.extend(L2)，该方法将参数L2的全部元素添加到L1的尾部
            areas.extend(areas_of_district)
            # 使用一个字典来存储区县和板块的对应关系
            for area in areas_of_district:
                area_dict[area] = district
        print("Area:", areas)
        print("District and areas:", area_dict)

        # 准备线程池用到的参数
        nones = [None for i in range(len(areas))]
        city_list = [city for i in range(len(areas))]
        args = zip(zip(city_list, areas), nones)

        # 针对每个板块启动一个线程来操作
        pool_size = thread_pool_size
        pool = threadpool.ThreadPool(pool_size)
        my_requests = threadpool.makeRequests(self.collect_area_second_hand_data, args)
        [pool.putRequest(req) for req in my_requests]
        pool.wait()
        pool.dismissWorkers(pool_size, do_join=True)  # 完成后退出

        # 计时结束，统计结果
        t2 = time.time()
        logger.info("second hand data crawling completed.")
        logger.info("Total crawl {0} areas.".format(len(areas)))
        logger.info("Total cost {0} second to crawl {1} data items.".format(t2 - t1, self.total_num))


if __name__ == '__main__':
    # 请调用根目录second_hand_main.py
    pass
