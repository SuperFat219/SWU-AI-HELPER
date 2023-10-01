import time

def add_list1 (int_list):
    ''' add a list of ints, iteration'''
    result_sum=0
    for num in int_list:
        result_sum = result_sum + num
    return result_sum

def add_list2(int_list):
    ''' add a list of ints, use builtin sum'''
    return sum(int_list)

def timer_func(int_list):
    t1 = time.time()
    add_list1(int_list)
    t2 = time.time()
    print("First function took {:7f} seconds".format(t2 - t1))

    t1 = time.time()
    add_list2(int_list)
    t2 = time.time()
    print("First function took {:7f} seconds".format(t2 - t1))
    

# big list
int_list = list(range(100000))

# run timer
timer_func(int_list)
