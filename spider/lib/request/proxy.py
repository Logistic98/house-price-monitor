#!/usr/bin/env python
# coding=utf-8
# 用于获取代理

import requests

from lib.spider.base_spider import *

# ProxyPool 爬虫代理IP池
# 项目地址：https://github.com/jhao104/proxy_pool

# 随机获取一个代理 可选参数: ?type=https 过滤支持https的代理
def get_proxy():
    if PROXY_OPEN_HTTPS:
        response = requests.get(PROXY_POOL_URL + "/get?type=https").json()
    else:
        response = requests.get(PROXY_POOL_URL + "/get").json()
    return response


# 获取并删除一个代理 可选参数: ?type=https 过滤支持https的代理
def get_pop_proxy():
    if PROXY_OPEN_HTTPS:
        response = requests.get(PROXY_POOL_URL + "/pop?type=https").json()
    else:
        response = requests.get(PROXY_POOL_URL + "/pop").json()
    return response


# 获取所有代理 可选参数: ?type=https 过滤支持https的代理
def get_all_proxy():
    if PROXY_OPEN_HTTPS:
        response = requests.get(PROXY_POOL_URL + "/all?type=https").json()
    else:
        response = requests.get(PROXY_POOL_URL + "/all").json()
    return response


# 查看代理数量
def get_proxy_count():
    response = requests.get(PROXY_POOL_URL + "/count").json()
    return response


# 删除指定代理
def delete_proxy(proxy):
    requests.get(PROXY_POOL_URL + "/?proxy={}".format(proxy))


if __name__ == '__main__':
    pass
