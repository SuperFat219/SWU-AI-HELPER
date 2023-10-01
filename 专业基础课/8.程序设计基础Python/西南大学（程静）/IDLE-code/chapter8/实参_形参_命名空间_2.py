def my_function(param_list):  #形参是一个list（可改变)
    print("function::before::形参::{},id:{}".format(param_list,id(param_list)))
    param_list[0] = 100
    print("function::after::形参::{},id:{}".format(param_list, id(param_list)))
    return


#--主程序-------
arg_list = [1,2,3] #实参是一个list，可改变
print("main::before::实参::{}, id:{}".format(arg_list,id(arg_list)))
my_function(arg_list)
print("main::before::实参::{}, id:{}".format(arg_list, id(arg_list)))
