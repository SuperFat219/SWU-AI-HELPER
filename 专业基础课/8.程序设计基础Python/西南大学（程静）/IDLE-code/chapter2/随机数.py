import random
#random.seed(123)
x = random.randint(1,100)
#random.seed(321)
y = random.randint(1,100)
#print(x,y)
r =int(input("请输入一个小于200的整数："))
#print(r)
if (x+y)==r:
    print("你好棒啊！")
else:
    print("你没有猜中！")
print("end!")
