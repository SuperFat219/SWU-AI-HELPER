# perfect number checker
# unless otherwise stated, variables are assumed to be of type int. Rule 4

# get a number to check
number_str = input("Please enter a number to check:")
number = int(number_str)

# find and sum up the divisors
divisor = 1
sum_of_divisors = 0
while divisor < number:
    if number % divisor == 0:
        sum_of_divisors = sum_of_divisors + divisor
    divisor = divisor + 1

#classify the result
if number == sum_of_divisors:
    print(number,"is perfect")
else:
    print(number,"is not perfect")

