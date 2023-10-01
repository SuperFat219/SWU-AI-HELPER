def a(x,f):#第一层函数
    x = 10
    f = 12.3
    def b(y):#第二层函数
        xx =100
        print("x=",x,",f=",f,"xx=",xx,"y=",y)
        print('打开文件B')
    return(b)


s=a(1,21.3)#首先要调用一次a函数，将a函数的返回值给s，这里也就是b函数
s(191)
