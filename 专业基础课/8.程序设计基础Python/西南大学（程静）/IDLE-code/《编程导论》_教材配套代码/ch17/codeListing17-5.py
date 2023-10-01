import time
import functools

def timer_decorator(func):
    ''' time a tuple of functions and report'''
    def wrapper(int_list):
        t1 = time.time()
        result = func(int_list)
        t2 = time.time()
        print("Function took {:7f} seconds".format(t2 - t1))
        return result
    return wrapper

# decorate add_list1
@timer_decorator
def add_list1 (int_list):
    ''' add a list of ints, iteration'''
    result_sum=0
    for num in int_list:
        result_sum = result_sum + num
    return result_sum

# @timer_decorator  comment out decoration   
def add_list2(int_list):
    ''' add a list of ints, use builtin sum'''
    return sum(int_list)

# and do it by hand
add_list2 = timer_decorator(add_list2)

@timer_decorator
def add_list3(int_list):
    ''' use a map to do the addition'''
    return functools.reduce(lambda x,y: x + y, int_list)

int_list = list(range(100000))

print(add_list1(int_list))
print(add_list2(int_list))
print(add_list3(int_list))
