def scope_function(param):
   new_int = param  # local variable created
   print("new_int value (in function) is:", new_int)

#主程序
arg = 27
scope_function(arg)
print("new_int value(in main) is:", new_int)  # ERROR!
