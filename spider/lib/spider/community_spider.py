#!/usr/bin/env python
# coding=utf-8
# 爬取小区数据的爬虫派生类

import re
import threadpool
from bs4 import BeautifulSoup
from lib.item.community import *
from lib.zone.city import get_city
from lib.spider.base_spider import *
from lib.utility.date import *
from lib.zone.area import *
from lib.utility.log import *
import pymysql

from lib.zone.clear_data import clear_data

pymysql.install_as_MySQLdb()


class CommunityBaseSpider(BaseSpider):

    def collect_area_community_data(self, city_name, area_name):

        # 获取数据
        xqs = self.get_community_info(city_name, area_name)
        for community in xqs:

            # 清洗数据
            text = community.text().strip()
            try:
                # 如果小区名里面没有逗号，那么总共是5项
                if text.count(',') == 4:
                    district, area, community, price, sale = text.split(',')
                elif text.count(',') < 4:
                    continue
                else:
                    fields = text.split(',')
                    district = fields[0]
                    area = fields[1]
                    community = ','.join(fields[2:-2])
                    price = fields[-2]
                    sale = fields[-1]
            except Exception as e:
                print(text)
                print(e)
                continue

            print("{0} {1} {2} {3} {4}".format(district, area, community, price, sale))

            time = get_time_string()
            source = SPIDER_NAME

            # 打开数据库连接
            db = pymysql.connect(host=HOST, user=USER, passwd=PASSWD, port=PORT, db=DB, charset=CHARSET)
            # 使用cursor()方法获取操作游标
            cursor = db.cursor()
            # sql语句
            sql = "INSERT INTO spider_community\
            (city, district, area, community, price, sale, time, source)\
            VALUES ('%s', '%s','%s','%s','%s','%s','%s','%s')" \
                  % (city_name, district, area, community, price, sale, time, source)

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
    def get_community_info(city, area):
        total_page = 1
        district = area_dict.get(area, "")
        chinese_district = get_chinese_district(district)
        chinese_area = chinese_area_dict.get(area, "")
        community_list = list()
        page = 'https://{0}.{1}.com/xiaoqu/{2}/'.format(city, SPIDER_NAME, area)
        print(page)

        response = get_response(page)
        html = response.content
        soup = BeautifulSoup(html, "lxml")

        # 获得总的页数
        try:
            page_box = soup.find_all('div', class_='page-box')[0]
            matches = re.search('.*"totalPage":(\d+),.*', str(page_box))
            total_page = int(matches.group(1))
        except Exception as e:
            print("\tWarning: only find one page for {0}".format(area))
            print(e)

        # 从第一页开始,一直遍历到最后一页
        for i in range(1, total_page + 1):
            page = 'https://{0}.{1}.com/xiaoqu/{2}/pg{3}'.format(city, SPIDER_NAME, area, i)
            print(page)  # 打印版块页面地址
            BaseSpider.random_delay()

            response = get_response(page)
            html = response.content
            soup = BeautifulSoup(html, "lxml")

            # 获得有小区信息的panel
            house_elems = soup.find_all('li', class_="xiaoquListItem")
            for house_elem in house_elems:
                price = house_elem.find('div', class_="totalPrice")
                name = house_elem.find('div', class_='title')
                on_sale = house_elem.find('div', class_="xiaoquListItemSellCount")

                # 继续清理数据
                price = price.text.strip().replace("元/m2","")
                if price == "暂无数据":
                    price = "0";
                name = name.text.replace("\n", "")
                on_sale = on_sale.text.replace("\n", "").strip().replace("套在售二手房", "")

                # 作为对象保存
                community = Community(chinese_district, chinese_area, name, price, on_sale)
                community_list.append(community)
        return community_list

    def start(self):
        city = get_city()
        type = "spider_community"
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
        my_requests = threadpool.makeRequests(self.collect_area_community_data, args)
        [pool.putRequest(req) for req in my_requests]
        pool.wait()
        pool.dismissWorkers(pool_size, do_join=True)  # 完成后退出

        # 计时结束，统计结果
        t2 = time.time()
        logger.info("community data crawling completed.")
        logger.info("Total crawl {0} areas.".format(len(areas)))
        logger.info("Total cost {0} second to crawl {1} data items.".format(t2 - t1, self.total_num))


if __name__ == "__main__":
    # 请调用根目录community_main.py
    pass
