count=int(input("请输入一个整数："))

while count>12:
    print("成立")
    count-=1
else:
    print('不成立') #当while条件不成立，直接跳到该处输出

print(count)
