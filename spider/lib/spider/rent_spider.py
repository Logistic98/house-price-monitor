#!/usr/bin/env python
# coding=utf-8
# 爬取租房数据的爬虫派生类

import re
from decimal import Decimal

import pymysql
import threadpool
from bs4 import BeautifulSoup
from lib.item.rent import *
from lib.spider.base_spider import *
from lib.utility.date import *
from lib.utility.log import logger
from lib.zone.area import *
from lib.zone.city import get_city
from lib.zone.clear_data import clear_data


class RentBaseSpider(BaseSpider):

    def collect_area_rent_data(self, city_name, area_name):

        # 获取数据
        rents = self.get_area_rent_info(city_name, area_name)
        for rent in rents:

            # 清洗数据
            text = rent.text().strip()
            try:
                # 如果小区名里面没有逗号，那么总共是6项
                if text.count(',') == 5:
                    district, area, descs, layout, size, price = text.split(',')
                elif text.count(',') < 5:
                    continue
                else:
                    fields = text.split(',')
                    district = fields[0]
                    area = fields[1]
                    descs = [2, -3]
                    layout = fields[-3]
                    size = fields[-2]
                    price = fields[-1]
            except Exception as e:
                print(text)
                print(e)
                continue

            print("{0} {1} {2} {3} {4} {5}".format(district, area, descs, layout, size, price))

            time = get_time_string()
            source = SPIDER_NAME

            # 打开数据库连接
            db = pymysql.connect(host=HOST, user=USER, passwd=PASSWD, port=PORT, db=DB, charset=CHARSET)
            # 使用cursor()方法获取操作游标
            cursor = db.cursor()
            # sql语句
            sql = "INSERT INTO spider_rent\
            (city, district, area, descs, layout, size, price, time, source)\
            VALUES ('%s', '%s', '%s','%s','%s','%s','%s','%s','%s')" \
                  % (city_name, district, area, descs, layout, size, price, time, source)

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
    def get_area_rent_info(city_name, area_name):
        matches = None
        """
        通过爬取页面获取城市指定版块的租房信息
        :param city_name: 城市
        :param area_name: 版块
        :return: 出租房信息列表
        """
        total_page = 1
        district_name = area_dict.get(area_name, "")
        chinese_district = get_chinese_district(district_name)
        chinese_area = chinese_area_dict.get(area_name, "")
        rent_list = list()
        page = 'https://{0}.{1}.com/zufang/{2}/'.format(city_name, SPIDER_NAME, area_name)
        print(page)

        response = get_response(page)
        html = response.content
        soup = BeautifulSoup(html, "lxml")

        # 获得总的页数
        try:
            if SPIDER_NAME == "lianjia":
                page_box = soup.find_all('div', class_='page-box')[0]
                matches = re.search('.*"totalPage":(\d+),.*', str(page_box))
            elif SPIDER_NAME == "ke":
                page_box = soup.find_all('div', class_='content__pg')[0]
                # print(page_box)
                matches = re.search('.*data-totalpage="(\d+)".*', str(page_box))
            total_page = int(matches.group(1))
            # print(total_page)
        except Exception as e:
            print("\tWarning: only find one page for {0}".format(area_name))
            print(e)

        # 从第一页开始,一直遍历到最后一页
        for num in range(1, total_page + 1):
            page = 'https://{0}.{1}.com/zufang/{2}/pg{3}'.format(city_name, SPIDER_NAME, area_name, num)
            print(page)
            BaseSpider.random_delay()
            response = get_response(page)
            html = response.content
            soup = BeautifulSoup(html, "lxml")

            # 获得有小区信息的panel
            if SPIDER_NAME == "lianjia":
                ul_element = soup.find('div', class_="content__list")
                house_elements = ul_element.find_all('div', class_="content__list--item")
            else:
                ul_element = soup.find('div', class_="content__list")
                house_elements = ul_element.find_all('div', class_="content__list--item")

            if len(house_elements) == 0:
                continue
            # else:
            #     print(len(house_elements))

            for house_elem in house_elements:
                if SPIDER_NAME == "lianjia":
                    price = house_elem.find('span', class_="content__list--item-price")
                    desc1 = house_elem.find('p', class_="content__list--item--title")
                    desc2 = house_elem.find('p', class_="content__list--item--des")
                else:
                    price = house_elem.find('span', class_="content__list--item-price")
                    desc1 = house_elem.find('p', class_="content__list--item--title")
                    desc2 = house_elem.find('p', class_="content__list--item--des")

                try:
                    if SPIDER_NAME == "lianjia":
                        price = price.text.strip().replace(" ", "").replace("元/月", "")
                        desc1 = desc1.text.strip().replace("\n", "")
                        desc2 = desc2.text.strip().replace("\n", "").replace(" ", "")
                        infos = desc1.split(' ')
                        descs = infos[0]
                        layout = infos[1]
                        size = desc2.split('/')[1].replace("㎡", "")
                    elif SPIDER_NAME == "ke":
                        price = price.text.strip().replace(" ", "").replace("元/月", "")
                        desc1 = desc1.text.strip().replace("\n", "")
                        desc2 = desc2.text.strip().replace("\n", "").replace(" ", "")
                        infos = desc1.split(' ')
                        descs = infos[0]
                        layout = infos[1]
                        size = desc2.split('/')[1].replace("㎡", "")

                    try:
                        float(size)    # 部分数据该位置不是size信息，把它转换成0入库（暂无数据）
                    except:
                        size = "0"

                    # 作为对象保存
                    rent = Rent(chinese_district, chinese_area, descs, layout, size, price)
                    rent_list.append(rent)
                except Exception as e:
                    print("=" * 20 + " page no data")
                    print(e)
                    print(page)
                    print("=" * 20)
        return rent_list

    def start(self):
        city = get_city()
        type = "spider_rent"
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
            # 使用一个字典来存储区县和板块的对应关系,
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
        my_requests = threadpool.makeRequests(self.collect_area_rent_data, args)
        [pool.putRequest(req) for req in my_requests]
        pool.wait()
        pool.dismissWorkers(pool_size, do_join=True)  # 完成后退出

        # 计时结束，统计结果
        t2 = time.time()
        logger.info("rent data crawling completed.")
        logger.info("Total crawl {0} areas.".format(len(areas)))
        logger.info("Total cost {0} second to crawl {1} data items.".format(t2 - t1, self.total_num))


if __name__ == '__main__':
    # 请调用根目录rent_main.py
    pass
