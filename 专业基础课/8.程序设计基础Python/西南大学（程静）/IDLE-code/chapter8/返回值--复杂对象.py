def tu_function(param_list):
    param_list.sort()
    tu = tuple(param_list)
    return tu

def tuple_function():
    return 1,2,[4,5]

#--主程序-----------
arg_list = [10,2,4,14,5]
tup_res_1 = tu_function(arg_list)
print("元组1：",tup_res_1)
print("元组1：",tu_function(arg_list))
tup_res_2 = tuple_function()
print("元组类型：",type(tup_res_2))
print("元组2：",tuple_function())