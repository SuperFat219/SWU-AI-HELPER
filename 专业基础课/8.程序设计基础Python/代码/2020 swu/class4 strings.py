'''
txs='dfaf'
wxw='safsa'
print(txs+'\n'+wxw)
'''
'''
#matplotlib库使用
import matplotlib as mpl
import matplotlib.pyplot as plt
import numpy as np
import math
x=np.linspace(-2*math.pi,2*math.pi,10000)
y=[]
for i in x:
    y.append(math.sin(i))
plt.rcParams['font.sans-serif']=['STSONG']
plt.rcParams['axes.unicode_minus']=False
plt.figure(figsize=(8,6))
plt.xlabel('时间/t')
plt.ylabel('波动指数/a')
plt.plot(x,y,color='blue')
plt.show()
'''
'''
str='i have a lovely dog!it is not your cat!it is my cat!'
str2='cat'
i=1
while i>=1:
    if str.find(str2,i+1)!=-1:
        print(str.find(str2,i+1))
        i=str.find(str2,i+1)
    else:
        break
'''
#将一张网页变成一个长的字符串，找出关键词出现的所有位置
'''
import requests
url='https://baike.baidu.com/item/%E5%91%A8%E6%9D%B0%E4%BC%A6/129156?fr=aladdin'
res=requests.get(url)
text=res.text
print(text.find('html'))
'''
#查找单个字符第一次出现下标
#查找单词位置第一次出现下标
#查找单个字符出现的所有位置
#查找单词出现的所有位置
'''
import jieba as jb
string='the cloud is so clear,you are so beautiful,my pretty girl'
words=jb.lcut(string)
#1 and #3.solution:


a=string.find('so')
print(a)
print()
i=0
str='so'
while i<len(string):
    b=string.find(str,i)
    if b==-1:
        break
    print(b)
    i=string.find(str,i)+1
'''
'''
import jieba as jb
string='the cloud is so clear,you are so beautiful,my pretty girl'
words=jb.lcut(string)
list2=list(enumerate((words)))
for i in range(len(list2)-1):
    if list2[i][1]=='so':
        print(list2[i][0])
'''

'''
txt = open('temp.txt','r')
f=open('output.txt','w')
for line_str in txt:
    new_str=''
    line_str=line_str.strip()
    for ch in line_str:
        new_str=ch+new_str
    print(new_str,file=f)
    print()
txt.close
'''
#Transform a name from the order of  ‘First Middle Last’  to the order of  ‘Last, First Middle’ 
#Code:
string='First Middle Last'
first,second,third=string.split() 
print(string.split())
                                #Python split() 通过指定分隔符对字符串进行切片，
                                  #如果参数 num 有指定值，则分隔 num+1 个子字符串
                                  #str.split(str="", num=string.count(str)).
                                  #str -- 分隔符，默认为所有的空字符，包括空格、换行(\n)、制表符(\t)等。
                                  #num -- 分割次数。默认为 -1, 即分隔所有。
                                  #返回分割后的字符串列表。
print(third,second,first)