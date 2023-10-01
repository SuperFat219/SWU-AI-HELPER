    # classify the number based on its divisor sum
    if number == sum_of_divisors:
        print(number,"is perfect")
    if number < sum_of_divisors:
        print(number,"is abundant")
    if number > sum_of_divisors:
        print(number,"is deficient")
    number += 1
