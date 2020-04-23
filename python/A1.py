#!/usr/bin/env python
# -*- encoding: utf-8 -*-

"""
@author: d00ms(--)
@file: A1.py
@time: 2020-4-23 9:49
@desc: 即一个字符串数组的全部组合，而非全排列。
"""

class Solution:
    def __init__(self):
        self._res = []

    def allCombines(self, array):
        if len(array) == 0:
            return []
        self.combine(array, 0, [])
        return self._res

    def combine(self, array, sp, tmp):
        if sp == len(array):
            return
        for i in range(sp, len(array)):
            tmp.append(array[i])
            self._res.append(''.join(tmp))
            self.combine(array, i + 1, tmp)
            tmp.pop()


if __name__ == "__main__":

    ans = Solution().allCombines('a,b,c,d'.split(','))
    pass
