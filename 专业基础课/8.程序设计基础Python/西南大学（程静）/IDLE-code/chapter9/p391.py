def f1 (my_dict):
    temp = 0
    for value in my_dict.values():
        temp = temp + value
    return temp

def f2 (my_dict):
    temp = ''
    for key in my_dict:
        if temp < key:
           temp = key
    return temp

def f3(my_dict,k,v):
    if k in my_dict:
       my_dict[k]=v

a_dict={'bill':1,'rich':2,'fred':10,'walter':20}

print(f1(a_dict)) # Line 1
print(f2(a_dict)) # Line 2
print(None == f3(a_dict,'bill',-1)) # Line 3
print(a_dict) # Line 
