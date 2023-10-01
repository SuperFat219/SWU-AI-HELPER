def func1(multiplier=5,reps=3):
    result_list=[]
    for i in range(1,reps+1):
        result_list.append(i*multiplier)
    return multiplier,result_list

print(func1(10)) # Line 1
print(func1(reps=4)) # Line 2
print(func1(multiplier=3))# Line 3
print(func1(7,5)) # Line 4
