#!/usr/bin/env python
# coding=utf-8
# 抓取数据之前先清空指定城市的指定类型当日数据（防止多次抓取导致数据重复）

import pymysql

from lib.spider.base_spider import *
from lib.utility.date import get_date_string
from lib.utility.log import logger


def clear_data(city, type):
    date = get_date_string()

    # 打开数据库连接
    db = pymysql.connect(host=HOST, user=USER, passwd=PASSWD, port=PORT, db=DB, charset=CHARSET)
    # 使用cursor()方法获取操作游标
    cursor = db.cursor()
    # sql语句（type即表名）
    sql = "DELETE FROM " + type + " WHERE city='%s'" % (city) + " AND substring(time,1,8) ='%s'" % (date)

    try:
        # 打印实际执行的sql，调试使用
        # print("-----执行------" + sql)
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

    logger.info(city + " " + type + " data of " + date + " has been cleared")


if __name__ == '__main__':
    pass
