def f1(s1,s2,op=4):
    if op == 1:
       temp = s1.intersection(s2)
    elif op == 2:
       temp = s1.difference(s2)
    else:
       temp = s1.union(s2)
    return temp

set1 = set('abc')
print(set1)
set2 = set('cd')
print(set2)

print(f1(set1,set2)) # Line 1
print(f1(set1,set2,1)) # Line 2
print(f1(set1,set2,2)) # Line 3
