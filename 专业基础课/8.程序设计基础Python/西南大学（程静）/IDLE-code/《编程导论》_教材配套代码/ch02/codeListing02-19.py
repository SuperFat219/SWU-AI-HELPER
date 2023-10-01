    # classify the number based on its divisor sum
    if number == sum_of_divisors:
        print(number,"is perfect")
    elif number < sum_of_divisors:
        print(number,"is abundant")
    else:
        print(number,"is deficient")
    number += 1
