'''
#seed() 方法改变随机数生成器的种子，可以在调用其他随机模块函数之前调用此函数。
random.seed( 10 )
print "Random number with seed 10 : ", random.random()
# 生成同一个随机数
random.seed( 10 )
print "Random number with seed 10 : ", random.random()
'''
#【问题描述】假设目前的世界人口有x亿，按照每年0.1%的增长速度，n年后将有多少人？
#【输入格式】一行两个正整数x和n，之间有一个空格。其中1<=x<=100, 1<=n<=100。
#【输出格式】一行一个整数，表示答案。以亿为单位，保留小数点后4位。
'''
m,n=map(int,input('输入两个正整数：（用空格隔开）').split())
for i in range(n):
    m=m*(1+0.1)
print(n,'年后人口数为{:.4}'.format(m))
'''
#【问题描述】输入若干整数，以0结尾，统计其中有多少个正整数
#【输入格式】一行若干整数，最后一个为0。
#【输出格式】一行一个整数，表示输入数据中正整数的个数
'''
a=input('输入一行整数，用空格隔开,以0结尾').split()
n=0
for i in a:
    if int(i)>0:
        n+=1
print(n)
'''
#【问题描述】小华在去年赚了一大笔钱。他想把这些钱用于投资，并对自己能得到多少收益感到好奇。
# 已知投资的年利率为r(0~20之间的整数），小华现有m元钱。他想知道投资多少年后，他的钱将会超过y元。
#【输入格式】一行三个整数：r、m、y，相邻两个整数之间用以空格分隔。m在100~1000000之间的整数
#【输出格式】一行一个整数，即要投资的年数。（y：1~400）
'''
r,m,y=map(int,input('输入年利率(0-20)，现有金额，上限').split())
i=1
while i>=1:
    m=(1+r/100)*m
    if m>y:
        print(i)
        break 
    else:
        i+=1
'''
#完全数
'''
def perfect(x):
    sum=0
    for i in range(1,x):
        if x%i==0:
            sum+=i 
    return sum

for i in range(1,50000):
    if perfect(i)==i:
        print(i)
'''
#素数
'''
def prime_number(x):
    for i in range(2,x):
        if x%i==0:
            return False
    return True
for i in range(1,100):
    if prime_number(i)==True:
        print(i,end=' ')
'''
'''
a,b,c=input('print').split()
print(a)
print(b)
print(c)
'''
#1. 斐波那契数列问题
#2. 求一堆数中的最大数和最小数问题
#3. 将一个整数的各个位置的数取出来？
#    阿姆斯特朗数、水仙花数。。。。。
#4. 设计程序：利用π/4 = 1–1/3+1/5–1/7 + ... 求π的近似值。要求一直计算到所用的最后两项的差小于0.00001。提示：通项公式为(–1)n/(2n–1)。

#1.
'''
f1=1
f2=1
f3=2
print(f1,f2,f3,end=' ')
while True:
    f1=f2
    f2=f3
    f3=f1+f2
    print(f3,end=' ')
    if f3>100:
        break
    '''
#2.
'''
list=[1,3,5,7,5,3,1,4,5,556,657,45,234,34,346,89,989,98980,78,98]
def bigger(x1,x2):
    if x1>=x2:
        return x1
    else:
        return x2
bigger_number=bigger(list[0],list[1])
for i in range(len(list)):
    if list[i]>=bigger_number:
        bigger_number=list[i]
print(bigger_number)
'''
#3.
#水仙花数：
'''for i in range(1,999):
    a=i%10
    b=(i//10)%10
    c=((i//10)//10)%10
    if i==a**3+b**3+c**3:
        print(i)
'''
'''
for i in range(100,999):
    while i !=0:
        s=0 
        a=i%10
        s+=a
        i//10
    if i==s:
        print(i)
        '''


#设计程序：利用π/4 = 1–1/3+1/5–1/7 + ... 求π的近似值。要求一直计算到所用的最后两项的差小于0.00001。提示：通项公式为(–1)n/(2n–1)。
'''
法一：
def my_abs(x):
    if x > 0:
        return x
    else:
        return -x
n = 0
s = 0
sigh = 1
while True:
    n = n + 1
    b = (-1)**(n - 1) / (2 * n - 1)
    c = (-1)**(n + 1) / (2 * n + 1)
    s = s + b
    y = my_abs(b) - my_abs(c)
    if y < 0.000000000001:
        break
print(4 * s)
'''
'''
#法二：
n=3
temp0=1
temp1=-1/3
sigh=1
s=2/3
while abs(temp0)-abs(temp1)>=0.000000001:
    temp0=temp1
    temp1=sigh/(2*n-1)
    s=s+temp1
    sigh=(-1)*sigh
    n+=1
print(4*s)
'''