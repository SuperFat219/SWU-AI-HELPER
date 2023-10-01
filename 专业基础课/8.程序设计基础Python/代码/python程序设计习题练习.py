#1.题目：古典问题：有一只兔子，从出生后第3个月起每个月都生一只兔子，
#小兔子长到第三个月后每个月又生一只兔子，假如兔子都不死，问每个月的兔子总数为多少？
#Code:
'''
month=int(input('print your ideal month:'))
f1=1
f2=1
f3=2
if month >=3:
    for i in range (2,month):
        f3=f1+f2
        f1=f2
        f2=f3
        output_sum=f3
    print('第'+str(month)+'个月的兔子总数为'+str(output_sum))
elif month==1:
    print('本月为起始月，只有一只兔子')
elif month==2:
    print('小兔子还没开始产仔哦，仍然只有一只兔子')
'''
#2.题目：判断101-200之间有多少个素数，并输出所有素数。
#Code:
'''
prime_number=[]
for i in range(101,201):
    remainder=[]
    for j in range(2,i):
        b=i%j
        remainder.append(b)
    if 0 not in remainder:
        prime_number.append(i)
length=len(prime_number)
print('101-200之间有'+str(length)+'个素数')
print('所有素数为',end='')
for i in prime_number:
    print(i,end=' ')
'''
'''
n = int(input("请输入一个正整数n:"))
if n < 2:           #判断是否大于1的整数，且1不是素数
    print("%d不是素数！"%n)
else:
    for i in range(2,n):
        if n % i == 0:    #判断2——i是否有能被整除
            print("%d不是素数！"%n)
            break
    else:
        print("%d是素数！"%n)
'''
#3题目：打印出所有的“水仙花数”，所谓“水仙花数”是指一个三位数，其各位
#数字立方和等于该数本身。例如：153是一个“水仙花数”，因为153=1的三次方＋5的三次方＋3的三次方。
#Code:
'''
for i in range(100,1000):
    a=i//100
    b=i%100//10
    c=i%100%10
    if i==a**3+b**3+c**3:
        print(i,end=' ')
'''
#!!!#4题目：将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。
#Code:
#Solution1:
# python / 除法运算永远返回一个浮点数
'''
n = int(input('请输入一个整数：'))
print('%d=' % n, end='')
while n > 1:
    for i in range(2, n + 1):
        if n % i == 0:
            n =int(n/i)
            if n == 1:
                print('%d' % i, end='')
            else:
                print('%d*' % i, end='')
print()        
'''
#Solution2:
#sqrt(x): 返回x的平方根
'''
from math import sqrt
while 1:
    n = int(input('请输入整数：'))
    print("%d = " % n, end='')
    while 1:
        for i in range(2, int(sqrt(n) + 1)):
            if n % i == 0:
                print('%d*' % i, end='')
                n = int(n / i)
                break
        else:
            print(n)
            break
'''

#5题目：利用条件运算符的嵌套来完成此题：学习成绩>=90分的同学用A表示，60-89分之间的用B表示，60分以下的用C表示。
#Code:略

#6题目：输入两个正整数m和n，求其最大公约数和最小公倍数。
# map() 会根据提供的函数对指定序列做映射.第一个参数 function 以参数序列中的每一个元素调用 function 函数，返回包含每次 function 函数返回值的新列表。
'''
map()实例：
    1.def square(x):            # 计算平方数
      return x ** 2
    map(square, [1,2,3,4,5])   # 计算列表各个元素的平方  >>>[1, 4, 9, 16, 25]
    2.map(lambda x: x ** 2, [1, 2, 3, 4, 5])  # 使用 lambda 匿名函数   >>>[1, 4, 9, 16, 25]
    3.提供了两个列表，对相同位置的列表数据进行相加
    map(lambda x, y: x + y, [1, 3, 5, 7, 9], [2, 4, 6, 8, 10])  >>>[3, 7, 11, 15, 19]
'''
#一次输入多个值方法：
'''
a,b =input('输入a,b空格隔开:').split()             #此时a,b为str型
a,b =map(int,input('输入a,b空格隔开:').split())    #此时a,b为int型
a, b, c = map(int, input('输入a,b,c空格隔开').split())
'''
#Code1:求最大公约数
#Solution1:
'''
m,n=map(int,input('输入两个正整数（用空格隔开）：').split())
remainder_m=[]
remainder_n=[]
for i in range(2,m+1):
    if m%i==0:
        remainder_m.append(i)
for x in remainder_m:
    if n%x==0:
        remainder_n.append(x)
Greatest_common_divisor=max(remainder_n)
print(str(m)+'和'+str(n)+'的最大公约数为：'+str(Greatest_common_divisor))
'''
#Solution2:
'''
def hcf(x, y):
   if x > y:
       smaller = y
   else:
       smaller = x
   for i in range(1,smaller + 1):
        if((x % i == 0) and (y % i == 0)):
            hcf = i   #不断迭代新值，直到找到最大值
   return hcf
num1 = int(input("输入第一个数字: "))
num2 = int(input("输入第二个数字: "))
print( num1,"和", num2,"的最大公约数为", hcf(num1, num2))
'''
#Code2：求最小公倍数：Least common multiple
'''
m,n=map(int,input('输入两个正整数（用空格隔开）').split())
def lcm(x, y):
    if x > y:
        greater = x
    else:
        greater = y
    while(True):
        if((greater % x == 0) and (greater % y == 0)):
            lcm = greater
            break
        greater += 1
    return lcm
print(lcm(m,n))
'''
#7题目：输入一行字符，分别统计出其中  英文字母、空格、数字和其它字符的个数
#Code:
'''
string=input('任意输入一行字符(仅限英文输入)：')
english_word=[]
else_string=[]
number=[]
n=0
for i in string:
    if i.isspace()==True:        #如果字符串中只包含空格，则返回 True，否则返回 False.
        n+=1
    elif i.isdigit()==True:   #i.isdigit()如果字符串只包含数字则返回 True 否则返回 False。
        number.append(i)
    elif i.isalpha()==True:   #i.isalpha()如果字符串至少有一个字符并且所有字符都是字母则返回 True，否则返回 False。
        english_word.append(i)
    else:
        else_string.append(i)
print('英文字母的个数为%d'%len(english_word),'空格的个数为%d'%n,'数字的个数为%d'%len(number),'其他字符的个数为%d'%len(else_string))
'''
#8题目：求s=a+aa+aaa+aaaa+aa…a的值，其中a是一个数字。例如2+22+222+2222+22222(此时共有5个数相加)，几个数相加由键盘控制。
#Code:
'''
a=int(input('输入一个正整数（0<a<10）：'))
frequency=int(input('输入相加数的个数：'))
s=0
b=a
print('%d+'%a,end='')
for i in range(1,frequency+1):
    a=b*(10**i)+a
    s+=a
    if i!=frequency:
        print('%d+'%a,end='')
    else:
        print('%d'%a,end='')
print('=%d'%s)
'''

#9题目：一个数如果恰好等于它的因子之和，这个数就称为“完数”。例如6=1＋2＋3.编程找出1000以内的所有完数。
#Code:
'''
for i in range(1,1001):
    s=0
    for j in range(1,i):
        if i%j==0:
            s+=j
    if s==i:
        print(i,end=' ')
'''
#10题目：一球从100米高度自由落下，每次落地后反跳回原高度的一半；再落下，求它在第10次落地时，共经过多少米？第10次反弹多高？
#Code:
'''
initial_height=100
bounce_height=50
distance_sum=150
n=2
while n>=2:
    initial_height=bounce_height
    bounce_height=bounce_height/2
    distance_sum=initial_height+bounce_height+distance_sum
    n+=1
    if n==11:
        break
print(bounce_height)
print('第十次落地时经过了%d米'%(distance_sum-bounce_height))
'''
#11题目：一只猴子摘了N个桃子第一天吃了一半又多吃了一个,第二天又吃了余下的一半又多吃了一个,到第十天的时候发现还有一个.求N
#Code:
'''
n=0
while True:
    n+=1
    j=n
    for i in range(1,11):
        j=j/2-1
    if j==1:
        print(n)
        break
'''
#12题目：打印出如下图案（菱形）
# *
# ***
# ******
# *********
# ******
# ***
# *
#Code:
'''
print('*')
for i in range(2,5):
    for j in range(1,(i-1)*3+1):
        print('*',end='')
    print()
for i in range(1,3):
    for j in range(1,(3-i)*3+1):
        print('*',end='')
    print()
print('*')
'''
#13题目：一个5位数，判断它是不是回文数。即12321是回文数，个位与万位相同，十位与千位相同。
#Self_Code:
'''
number=input('输入一个五位数：')
list=[]
for i in number:
    list.append(i)
if (list[0]==list[4]) and (list[1]==list[3]):
    print('%s是回文数。'%number)
else:
    print('%s不是回文数。'%number)
'''
#Solution2:
'''
number=int(input('输入一个五位数：'))
a=number//10000
b=number%10000//1000
c=number%10000%1000//100
d=number%10000%1000%100//10
e=number%10000%1000%100%10
print(a,b,c,d,e)
if a==e and b==d:
    print('%d是回文数'%number)
else:
    print('wrong')
'''
#14题目：请输入星期几的第一个字母来判断一下是星期几，如果第一个字母一样，则继续判断第二个字母。
#Code:略

#15题目：Press any key to change color, do you want to try it. Please hurry up!
'''
import msvcrt
color=
msvcrt.getch()

'''

#16题目：对10个数进行排序
'''
list=[]
for i in range(1,10):
    a=float(input('print a number:'))
    list.append(a)
list.sort(reverse=True)
print(list)
'''
#17题目：求一个3*3矩阵对角线元素之和
'''
import numpy as np
a,b,c=map(int,input('输入第一行的三个数(逗号隔开)').split(','))
d,e,f=map(int,input('输入第二行').split(','))
g,h,j=map(int,input('输入第三行').split(','))
line1=[a,b,c]
line2=[d,e,f]
line3=[g,h,j]
sum=line1[0]+line2[1]+line3[2]
print(sum)
'''
#18题目：有一个已经排好序的数组。现输入一个数，要求按原来的规律将它插入数组中。
'''
1、int() 向下取整
n = 3.85
print(int(n))
3
2、round() 四舍五入
n = 3.85
print(round(3.85))
4
3、floor() 向下取整
n = 3.85
print(floor(3.85))
3
4、ceil()向上取整
n = 3.85
print(ceil(3.85))
5、modf() 分别取整数部分和小数部分
n = 3.85
3 print(math.modf(n))
(0.85, 3.0)
6、python中保留一位小数的方法
（1）print(round(10/3,1))
（2）print('%.1f'%(10/3))
（3）print(format((10/3),'.1f'))
>>> 3.3
'''
'''
list1=[1,3,5,6,7,8,9]
number=float(input('输入一个数：'))
if number-int(number)==0:
    number=int(number)
if number>list1[-1]:
    list1.append(number)
else:
    for i in list1:
        location=list1.index(i)
        if number<i:
            list1.insert(location,number)
            break
print(list1)
'''
#19题目：将一个数组逆序输出。
'''
list=[1,2,3,4,5,7,8,9,6,77,7,8]
#list.sort(reverse=True)#先排序后逆序
#solution1:list.reverse()
print(list[::-1])
print(''.join('%s' %id for id in list))#列表里有数字，不能直接转化成字符串，先将数字转换成字符串形式
'''

#小包最近迷上了一款叫做雀魂的麻将游戏，但是这个游戏规则太复杂，小包玩了几个月了还是输多赢少。
#于是生气的小包根据游戏简化了一下规则发明了一种新的麻将，只留下一种花色，并且去除了一些特殊和牌方式(例如七对子等)，
# 具体的规则如下：
#1.总共有36张牌，每张牌是1~9。每个数字4张牌。
#2.你手里有其中的14张牌，如果这14张牌满足如下条件，即算作和牌
#·14张牌中有2张相同数字的牌，称为雀头。•除去上述2张牌，剩下12张牌可以组成4个顺子或刻子。顺子的意思是递增的连续3个数字牌(例如234,567等)，
# 刻子的意思是相同数字的3个数字牌(例如11,777)#例如：
#11122266677799可以组成1，2，6，7的4个刻子和9的雀头，可以和牌
#11112233567789用1做雀头，组123,123，567，789的四个顺子，可以和牌
#11122233356779无论用1237哪个做雀头，都无法组成和牌的条件。
#现在，小包从36张牌中抽取了13张牌，他想知道在剩下的23张牌中，再取一张牌，取到哪几种数字牌可以和牌
#Code:
'''
import random
from collections import Counter  #引入Counter
win_cards = []


def initial_preparation():
    total_cards = [
        1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6,
        7, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9
    ]
    global your_cards
    global choice_card
    global your_cards2
    your_cards = []
    for i in range(13):
        single_card = random.choice(total_cards)
        your_cards.append(single_card)
        total_cards.remove(single_card)
    your_cards = sorted(your_cards)
    print('你的13张手牌是：', your_cards, '现在开始听牌')
    while True:
        choice_card = random.choice(total_cards)
        #print('你抽到的第14张牌是：',choice_card)
        total_cards.remove(choice_card)
        your_cards.append(choice_card)
        your_cards.sort()
        your_cards2 = your_cards[:]
        #print('你现在的手牌是：',your_cards)
        win1()
        your_cards.remove(choice_card)
        if total_cards == []:
            break


def win1():
    b = dict(Counter(your_cards2))
    n = 0
    j = 0
    for key, value in b.items():
        if value == 3:
            n += 1
        elif value == 2:
            j += 1
    if n == 4 and j == 1:
        #print('win1你的手牌可以胡牌')
        win_cards.append(choice_card)
    else:
        win2()


def win2():  #·14张牌中有2张相同数字的牌，称为雀头。•除去上述2张牌，剩下12张牌可以组成4个顺子
    c = dict(Counter(your_cards2))
    n = 0
    for key, value in c.items():
        if value == 2:
            n += 1
    for i in range(n):
        b = dict(Counter(your_cards2))
        for key, value in b.items():
            if value == 2:
                remove_number = key
                your_cards2.remove(key)
                your_cards2.remove(key)
                break
        a = cards_continuously()
        if a == 4:
            #print('win2你的牌可以胡牌',remove_number)
            win_cards.append(choice_card)
            break
        else:
            your_cards2.append(remove_number)
            your_cards2.append(remove_number)


def cards_continuously():
    continuous_number = 0
    #将删除后的列表依次进行顺子查找
    for i in your_cards2[:]:
        if ((i + 1 in your_cards2) and (i + 2 in your_cards2)
                and (i in your_cards2)):
            continuous_number += 1
            for m in range(i, i + 3):
                a = your_cards2.index(m)
                your_cards2.pop(a)
    return continuous_number


def main():
    initial_preparation()
    conclusion()


def conclusion():
    global win_cards
    if win_cards != []:
        list = []
        c = dict(Counter(win_cards))
        for key, values in c.items():
            list.append(key)
        list.sort()
        print('抽到', ' '.join('%s' % i for i in list), '可以胡牌')
        win_cards=[]
    else:
        print('无法胡牌')
        
main()

'''

#TechFlow>
#题意
#我叫王大锤,是一名特工。我刚刚接到任务:在字节跳动大街进行埋伏,抓捕恐怖分子孔连顺。和我一起行动的还有另外两名特工,我提议
#1.我们在字节跳动大街的N个建筑中选定3个埋伏地点。
#2.为了相互照应,我们决定相距最远的两名特工间的距离不超过D。
#我特喵是个天才!经过精密的计算,我们从X种可行的埋伏方案中选择了一种。这个方案万无一失,颤抖吧,孔连顺
#万万没想到,计划还是失败了,孔连顺化妆成小龙女,混在cosplay的队伍中逃出了字节跳动大街。只怪他的伪装太成功了,就是杨过本人来了也发现不了的
#请听题:给定N(可选作为埋伏点的建筑物数) D(相距最远的两名特工间的距离的最大值)以及可选建筑的坐标,
#计算在这次行动中,大锤的小队有多少种埋伏选择
#注意:
#1.两个特工不能埋伏在同一地点
#2.三个特工是等价的:即同样的位置组合(A,B,C)
#只算一种埋伏方法,不能因“特工之间互换位置”而重复使用
import numpy as np
import random


def comparison(x1, x2, x3):
    list = []
    list.append(abs(x1 - x2))
    list.append(abs(x1 - x3))
    list.append(abs(x2 - x3))
    return int(max(list))


def initial():
    global list1
    global Optional_location_combination
    list1 = []
    Optional_location_combination = []
    N = int(input('可选为埋伏点的建筑物数为：'))
    D = int(input('相距最远特工距离的最大值限制为：'))
    for i in range(N):
        list1.append(i + 1)
    distance_calculation(D)


def judegment(x1, x2, x3):
    if x1 == x2 or x2 == x3 or x1 == x3:
        return False
    return True


def distance_calculation(x):
    for i in range(1, len(list1) + 1):
        location1 = i
        for j in range(1, len(list1) + 1):
            location2 = j
            for k in range(1, len(list1) + 1):
                location3 = k
                if judegment(location1, location2, location3) == True:
                    distance = comparison(location1, location2, location3)
                    if (distance <= x) and (set([
                            location1, location2, location3
                    ]) not in Optional_location_combination):
                        #print([location1,location2,location3])
                        Optional_location_combination.append(
                            set([location1, location2, location3]))


def main():
    initial()
    print('有', len(Optional_location_combination))
    #print(Optional_location_combination)


main()
