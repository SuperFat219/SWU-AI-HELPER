number_str = input("Enter an int:")
number = int(number_str)
count = 0
while number > 0:
     if number % 2 == 0:
        number = number // 2
     elif number % 3 == 0:
        number = number // 3
     else: # Line 1
        number = number - 1 # Line 2
     count = count + 1
     
print("Count is: ",count) # Line 3
print("Number is: ",number) # Line 4
