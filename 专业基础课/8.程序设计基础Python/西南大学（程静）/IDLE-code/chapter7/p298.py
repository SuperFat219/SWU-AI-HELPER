my_list = [4.76, 3.17, 10.08, 2.19]
new_list = []
a_list = []
for val in my_list:
    temp = str(val)
    a_list.append(temp.split('.'))
print("1111111:",a_list)

for val in a_list:
    new_list.append(int(val[0]))
print("@@@@@@@@:",new_list)

my_str = ':'.join(val)

print("my_list:",my_list) # Line 1
print("a_list:",a_list) # Line 2
print("new_list:",new_list) # Line 3
print("val:",val) # Line 4
print("my_str:",my_str) # Line 5
