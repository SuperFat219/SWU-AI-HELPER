x = int(input("请输入一个整数:"))

count=0
while 1:
    if(x<0):
        break
    x=x-2
    print(x,end=",")
    count=count+1

print()
print("count=",count,",x=",x)
