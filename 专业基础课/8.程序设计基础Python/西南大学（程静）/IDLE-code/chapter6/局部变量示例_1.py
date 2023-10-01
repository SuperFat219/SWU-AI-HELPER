def scope_function(a_int):
   new_int = a_int  # local variable created
   print("new_int value (in function) is:", new_int)

#主程序
scope_function(27)
print("new_int value(in main) is:", new_int)  # ERROR!
