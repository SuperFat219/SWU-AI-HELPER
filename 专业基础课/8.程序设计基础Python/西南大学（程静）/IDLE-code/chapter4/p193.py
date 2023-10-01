my_str = input("Input a string: ")
index_int = 0
result_str = ''
while index_int < (len(my_str) - 1): 
   if my_str[index_int] > my_str[index_int + 1]:
       result_str = result_str + my_str[index_int]
   else:
       result_str = result_str * 2
   index_int += 1

print("End of While\n")
print("result is:",result_str)
