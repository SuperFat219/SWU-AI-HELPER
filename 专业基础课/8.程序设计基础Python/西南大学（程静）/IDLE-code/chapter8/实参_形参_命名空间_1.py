def my_function(param):  #形参是一个int（不可改变)
    print("function::before::形参::{},id:{}".format(param,id(param)))
    param = 32
    print("function::after::形参::{},id:{}".format(param, id(param)))
    return


#--主程序-------
arg = 25 #实参是一个list，可改变
print("main::before::实参::{}, id:{}".format(arg,id(arg)))
my_function(arg)
print("main::before::实参::{}, id:{}".format(arg, id(arg)))
