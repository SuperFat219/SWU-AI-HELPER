user_str = input("Enter a positive integer:") # Line 1
my_int = int(user_str)
count = 0

while my_int > 0:
   if my_int % 2 == 1:
       my_int = my_int//2
   else:
       my_int = my_int - 1
   count = count + 1 # Line 2

print(count) # Line 3
print(my_int) # Line 4
