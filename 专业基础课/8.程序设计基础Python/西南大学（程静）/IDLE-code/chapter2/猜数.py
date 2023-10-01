import random

num = random.randint(0,100)

guess = int(input("请输入一个0~100之间的整数："))
while(True):
    if (guess ==num):
        print("猜对了!")
        break
    elif (guess>num):
        print("猜大了!")
        guess = int(input("请输入一个0~100之间的整数："))
    else:
        print("猜小了!")
        guess = int(input("请输入一个0~100之间的整数："))

print("程序结束!")
