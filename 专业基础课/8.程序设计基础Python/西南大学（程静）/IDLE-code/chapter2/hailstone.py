 # Generate a hailstone sequence
number_str = input("Enter a positive integer:")
number = int(number_str)
count = 0

print("Starting with number:",number)
print("Sequence is: ", end=' ')

while number > 1: 
   if number%2:
      number = number*3 + 1
   else: 
      number = number/2
   print(number,',',end=' ') 
   count +=1 

print()
print("Sequence is ",count," numbers long")
