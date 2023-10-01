# Newton's Method to calculate square root

# get three inputs from the user (two ints, 1 float)
# note not robust on bad input
num_str = input("Find the square root of integer: ")
num_int = int(num_str)
guess_str = input("Initial guess: ")
guess_int = int(guess_str)
tolerance_float = float(input("What tolerance: "))

original_guess_int = guess_int  # hang onto the original guess
count_int = 0                   # count the number of guesses

# do the algorithm steps as described above

# output the three original values, the number of
# iterations and the square root
print("Square root of",num_int," is: ",guess_int)
print("Took ",count_int," reps to get it to tolerance: ",tolerance_float)
print("Starting from a guess of: ", original_guess_int)
