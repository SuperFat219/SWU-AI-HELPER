def globalVar1():
    print("function::globalVar1::x=",x)  # x是全局变量
    return

def globalVar2():
    x = 100 
    print("function::globalVar2::x=",x)   # x是局部变量
                                # 和全局变量x同名，覆盖全局变量x的作用域
    return


#---主程序-------------
x = 20  #此x=20是一个全局变量，在所有函数内部都有效
print("主程序::全局变量x::before::x=",x)
globalVar1()
globalVar2()
print("主程序::全局变量x::after::x=", x)
