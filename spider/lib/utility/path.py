#!/usr/bin/env python
# coding=utf-8
# 获得当前目录结构,创建目录结构

import inspect
import os
import sys


def get_root_path():
    file_path = os.path.abspath(inspect.getfile(sys.modules[__name__]))
    parent_path = os.path.dirname(file_path)
    lib_path = os.path.dirname(parent_path)
    root_path = os.path.dirname(lib_path)
    return root_path


# 路径常量
ROOT_PATH = get_root_path()
LOG_PATH = ROOT_PATH + "/log"

if __name__ == "__main__":
    pass
