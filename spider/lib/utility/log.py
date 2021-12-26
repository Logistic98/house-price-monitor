#!/usr/bin/env python
# coding=utf-8
# 日志输出配置类（输出控制台和log文件）

import logging
from lib.utility.path import LOG_PATH

logger = logging.getLogger(__name__)
formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(message)s')

# 输出到控制台
console = logging.StreamHandler()
console.setLevel(logging.INFO)
console.setFormatter(formatter)
logger.addHandler(console)

# 输出到文件
logger.setLevel(level=logging.INFO)
handler = logging.FileHandler(LOG_PATH + "/log.txt")
handler.setLevel(logging.INFO)
handler.setFormatter(formatter)
logger.addHandler(handler)

if __name__ == '__main__':
    pass
