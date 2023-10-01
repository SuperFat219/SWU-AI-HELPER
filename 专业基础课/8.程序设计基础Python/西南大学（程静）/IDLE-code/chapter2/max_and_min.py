a =int(input("请输入第一个整数："))
b =int(input("请输入第二个整数："))
print(a,b)
if(a<b):
    temp=a
    a=b
    b=temp
print("(max,min)=",a,b,sep=',')
