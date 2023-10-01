'''
import math

number=float(input("please input a number"))
guess=float(input("please input a guess of the root"))
guess_before = number
            
while (math.fabs(guess-guess_before)>0.00001):
    guess_before = guess
    ave = (number/guess + guess)/2.0
    guess = ave

print(guess)
'''
def babylonian_square_root(x):
    guess_before = x
