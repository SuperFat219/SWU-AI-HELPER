#例一：给定一个有序数组，判断指定元素是否包含在该数组中
#Code：
import numpy as np
def binary_search(list,item):
    low=0
    high=len(list)-1
    while low<=high:
        average=int((low+high)/2)
        guess=list[average]
        if guess==item:
            return average
        elif guess<item:
            low=average+1  #索引值从0开始，只要不等于中间数，就从中间数的旁边一个开始查找
        else:
            high=average-1
    return None

number=float(input('输入你想查找的值'))
print(binary_search(list,number))
 