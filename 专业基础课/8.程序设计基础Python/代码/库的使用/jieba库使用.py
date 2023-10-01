#运用jieba库统计三国演义高频词语
import jieba as jb
txt=open(r'C:\\Users\\God\\Desktop\\test\\材料\\sanguo.txt','r',encoding='utf-8').read()
words=jb.lcut(txt)
counts={}
for word in words:
    if  len(word) == 1:    # 单个词语不计算在内
        continue
    else:
        counts[word] = counts.get(word, 0) + 1    # 遍历所有词语，每出现一次其对应的值加 1
#dict.get(key, default=None),default -- 如果指定键的值不存在时，返回该默认值。
        
items = list(counts.items())#将键值对转换成列表
#Python 字典(Dictionary) items() 函数以列表返回可遍历的(键, 值) 元组数组。list():将元组数据转化为列表
#items():
# 1.person={'name':'lizhong','age':'26','city':'BeiJing'}
        #for key,value in person.items():
        #print('key=',key,'value=',value)
#2.person={'name':'lizhong','age':'26','city':'BeiJing','blog':'www.jb51.net'}
#for x in person.items():    如果是 for x in person,则只返回键值name,age,city
#    print('x=',x)
items.sort(key=lambda x: x[1], reverse=True)    # 根据词语出现的次数进行从大到小排序
#1.list.sort( key=None, reverse=False)
# key -- 主要是用来进行比较的元素，只有一个参数，具体的函数的参数就是取自于可迭代对象中，指定可迭代对象中的一个元素来进行排序。
#reverse -- 排序规则，reverse = True 降序， reverse = False 升序（默认）。
#2.lambda:匿名函数，定义一个算法
# a=lambda x:x*x
#print(a)
#print(a(3))
#---->
#<function <lambda> at 0x0000000002093E18>
#9
'''
for i in range(15):
    word, count = items[i]
    print("{0:<5}{1:>5}".format(word, count))
#首先：'我的{0}叫{1}'.format(name,jack)，大括号里的数字，表示的是位置，也就是0对应的name,1对应的jack。
# 同理，题中0对应的是Word，1对应的是count。
#其次，冒号是引导符，后面跟的是格式控制方法。<表示左对齐，>表示右对齐，数字表示宽度。
#同理，题中<10表示左对齐，并占10个位置，>5表示右对齐，占5个位置。

#完整代码：
import jieba
txt=open(r'C:\Users\God\Desktop\test\材料\sanguo.txt','r',encoding='utf-8').read()
words=jieba.lcut(txt)
data={}
for word in words:
    if len(word)==1:
        continue
    else:
        data[word]=data.get(word,0)+1
items=list(data.items())
items.sort(key=lambda x:x[1],reverse=True)
for i in range(10):
    word,count=items[i]
    print('{0:<10}{1:>5}'.format(word,count))
'''