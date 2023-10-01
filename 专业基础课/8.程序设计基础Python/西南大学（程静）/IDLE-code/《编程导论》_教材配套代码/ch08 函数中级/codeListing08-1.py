# a function with a local variable
def scope_function (a_int):
    new_int = a_int    # local variable created
    print('new_int value (in function) is: ',new_int) 

# main program
scope_function(27)
print('new_int value is:',new_int)  # ERROR! (scope) 

