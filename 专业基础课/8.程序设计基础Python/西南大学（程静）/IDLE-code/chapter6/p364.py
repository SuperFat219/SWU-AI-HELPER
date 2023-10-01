def func1(list1,list2,str1):
    if len(list1)> 3:
        list1 = list1[:3]
    list2[0] = 'goodbye'
    str1 = ''.join(list2)
    print("ids:{:<15d},{:<15d},{:<15d}".format(id(list1),id(list2),id(str1)))

arg1_list = ['a','b','c','d']
arg2_list = ['hello','mother','and','father']
arg_str = 'sister'
print("############################")
print("ids:{:<15d},{:<15d},{:<15d}".format(id(arg1_list),id(arg2_list),id(arg_str)))
print("############################")

func1(arg1_list, arg2_list, arg_str)

print(arg1_list) # Line 1
print(arg2_list) # Line 2
print(arg_str) # Line 3

print("############################")
print("ids:{:<15d},{:<15d},{:<15d}".format(id(arg1_list),id(arg2_list),id(arg_str)))
