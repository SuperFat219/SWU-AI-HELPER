'''
#法一：
a=input('print a number:')
for i in a:
    print(i,end='  ')
'''
'''
#法二：
a=input('print a number:')
for i in range(len(a)):
    b=int(a)%10
    a=int(a)//10
    print(b)
'''
'''
#法三：
a=int(input('print your number:'))
list1=[]
while a!=0:
    b=a%10
    list1.append(b)
    a=a//10
list1.sort(reverse=True)
print(list1)
'''
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
法一：def my_abs(x):
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
#斐波那契数列是指这样的数列：第一个数和第二个数都为1， 接下来每个数都等于前面2个数之和。编程输入一个正整数k，输出斐波那契数列第k个数。
'''
a=1
b=1
d=2
number=int(input('print your number:'))
while True:
    d+=1
    c=a+b
    a=b
    b=c
    if number==c:
        print('这是数列中第'+str(d)+'个数'+str(number))
        break

'''
#【问题描述】假设目前的世界人口有x亿，按照每年0.1%的增长速度，n年后将有多少人？
#【输入格式】一行两个正整数x和n，之间有一个空格。其中1<=x<=100, 1<=n<=100。
#【输出格式】一行一个整数，表示答案。以亿为单位，保留小数点后4位。
'''
x=float(input('print the population:'))
n=int(input('print the year:'))
for i in range(1,n+1):
    x=x*(1+(0.1/100))
print('%.4f'%x)   #or:print(format(x,'.4f'))   (都进行四舍五入)
'''
#阿姆斯特朗数:n位正整数等于各位数的n次方之和
    #self_code:
'''
for x in range(1,1001):
    list=[]
    for i in str(x):
        list.append(i)
    n=len(list)
    s=0
    for i in range(1,n+1):
        c=int(list[i-1])**n
        s+=c
    if s==x:
        print(x,end=' ')
'''
    #Reference_code:
'''
num = int(input("请输入一个数字: "))
sum = 0
n = len(str(num))
temp = num
while temp > 0:
   digit = temp % 10
   sum += digit ** n
   temp //= 10  #temp=temp//10
if num == sum:
   print(num,"是阿姆斯特朗数")
else:
   print(num,"不是阿姆斯特朗数")
'''

#一串数字的逆序数
    #self_code:
'''
string_number=input('输入一串数字')
list=[]
if string_number.isdigit()!=True:
    string_number=input('重新输入')
for i in string_number:
    list.append(i)
b=len(list)
m=0
k=0
for i in list:
    for j in list[k:]:
        if j<i:
            m+=1
    k+=1
print(m)
'''
    #Reference_Code:

def inverse_number(string):
    'input a number which is string than,you will get the inverse number'
    ans = 0
    for i in range(len(string)):
        for j in range(i):
            if string[j] > string[i]:
                ans += 1
    return ans
print(inverse_number(input("Please input the number: ")))

#利用python进行矩阵运算
#numpy库：Numpy支持大量的维度数组和矩阵运算，对数组运算提供了大量的数学函数库！
#Numpy比Python列表更具优势，其中一个优势便是速度。在对大型数组执行操作时，Numpy的速度比Python列表的速度快了好几百。
# 因为Numpy数组本身能节省内存，并且Numpy在执行算术、统计和线性代数运算时采用了优化算法。
#Numpy的另一个强大功能是具有可以表示向量和矩阵的多维数组数据结构。
# Numpy对矩阵运算进行了优化，使我们能够高效地执行线性代数运算，使其非常适合解决机器学习问题。
#与Python列表相比，Numpy具有的另一个强大优势是具有大量优化的内置数学函数。
# 这些函数使你能够非常快速地进行各种复杂的数学计算，并且用到很少代码（无需使用复杂的循环），使程序更容易读懂和理解。
#注：在ndarray结构中，里面元素必须是同一类型的，如果不是，会自动的向下进行。
import numpy as np
x=np.array([[1,0,0],[2,0,0]])
print(x)
