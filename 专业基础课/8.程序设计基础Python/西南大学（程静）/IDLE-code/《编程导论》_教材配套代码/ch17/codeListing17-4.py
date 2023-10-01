import time
import functools

def add_list1 (int_list):
    ''' add a list of ints, iteration'''
    result_sum=0
    for num in int_list:
        result_sum = result_sum + num
    return result_sum

def add_list2(int_list):
    ''' add a list of ints, use builtin sum'''
    return sum(int_list)

def add_list3(int_list):
    ''' use a map to do the addition'''
    return functools.reduce(lambda x,y: x + y, int_list)

def timer_func(int_list, *func_tuple):
    ''' time a tuple of functions and report'''
    func_counter = 1
    
    for func in func_tuple:
        t1 = time.time()
        func(int_list)
        t2 = time.time()
        print("Function {} took {:7f} seconds".format(func_counter, t2 - t1))
        func_counter += 1

# big list
int_list = list(range(100000))

# run timer
timer_func(int_list, add_list1, add_list2, add_list3)
