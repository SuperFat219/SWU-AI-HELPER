x = int(input("请输入一个正整数："))

factor = 2
flag = True   #flag=1
while (factor<x):      #看看factor<x，还可以修改吗？
    if (x%factor == 0):
        flag = False    #flag=0
    factor += 1

if (flag):
    print("Is a Prime Number!")
else:
    print("Is Not a Prime Number!")
