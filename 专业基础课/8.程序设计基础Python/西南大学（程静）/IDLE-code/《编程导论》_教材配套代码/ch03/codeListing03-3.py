# Calculate the average of a sum of consecutive integers in a given range.

# input the value
limit_str=input("Range is from 1 to your input:")
#convert the input string to an input
limit_int = int(limit_str)
# assign 1 to the counting variable
count_int = 1
# assign 0 to the sum
sum_int = 0
# while loop runs while the counting variable is smaller than the input value
while count_int <= limit_int:
	# add the count and the sum, reassign to sum
        sum_int = sum_int + count_int
	# add one to the count
        count_int = count_int + 1
# calculate the average
average_float = sum_int/(count_int - 1)
# print the result
print("Average of sum of integers from 1 to",limit_int,"is", average_float)
