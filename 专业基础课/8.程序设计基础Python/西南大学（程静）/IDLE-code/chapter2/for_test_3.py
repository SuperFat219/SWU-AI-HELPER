the_max = int(input("Enter the upper limit:"))
the_sum = 0
extra = 0

for number in range(1,the_max):
    if number%2 and not number%3:
        the_sum += number
    else:
        extra =+1

print(the_sum)
print(extra)
