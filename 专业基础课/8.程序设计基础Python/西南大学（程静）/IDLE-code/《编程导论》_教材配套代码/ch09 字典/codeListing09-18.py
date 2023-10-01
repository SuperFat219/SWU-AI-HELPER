
global_var = 27

def outer_function(param_outer = 123):
    outer_var = global_var + param_outer

    def inner_function(param_inner = 0):
        # get inner, enclosed and global
        inner_var = param_inner + outer_var + global_var  
        # print inner namespace
        print('\n--- inner local namespace ---')
        for key,val in locals().items():
            print('{}:{}'.format(key,str(val)))
        return inner_var

    result = inner_function(outer_var)
    # print outer namespace
    print('\n--- outer local namespace ---')
    for key,val in locals().items():
        print('{}:{}'.format(key,str(val)))
    return result

result  = outer_function(7)
print('\n--- result ---')
print('Result:',result)
