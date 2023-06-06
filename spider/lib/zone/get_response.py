#!/usr/bin/env python
# coding=utf-8
# 通过requests请求抓取页面获得response的方法

import requests

from lib.request.headers import create_headers
from lib.request.proxy import get_all_proxy
from lib.spider.base_spider import PROXY_SWITCH, PROXY_OPEN_HTTPS
from lib.utility.log import logger


def get_response(url):
    headers = create_headers()
    if PROXY_SWITCH:
        try:
            proxies = get_all_proxy()
            if len(proxies) == 0:
                try:
                    response = requests.get(url, timeout=10, headers=headers)
                except Exception:
                    logger.error(
                        "Crawl failed. No proxy is available and direct connection fails, Please try again later.")
        except Exception:
            try:
                response = requests.get(url, timeout=10, headers=headers)
            except Exception:
                logger.error("Crawl failed. No proxy is available and direct connection fails, Please try again later.")
        else:
            for proxy in proxies:
                proxy = proxy.get('proxy')
                try:
                    if PROXY_OPEN_HTTPS:
                        response = requests.get(url, timeout=10, headers=headers, proxies={"https": "https://{}".format(proxy)})
                    else:
                        response = requests.get(url, timeout=10, headers=headers, proxies={"http": "http://{}".format(proxy)})
                    if response.status_code == 200:
                        break
                    else:
                        continue
                except Exception:
                    continue
    else:
        try:
            response = requests.get(url, timeout=10, headers=headers)
        except Exception:
            logger.error("Crawl failed. direct connection fails, Please try again later.")
    return response


if __name__ == "__main__":
    test = get_response('https://yt.lianjia.com/ershoufang')
    print(test)
