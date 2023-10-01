# Our implementation of the find function. Prints the index where 
# the target is found; a failure message, if it isn't found. 
# This version only searches for a single character.

river = 'Mississippi'
target = input('Input a character to find: ')
for index,letter in enumerate(river):          # for each index
    if letter == target:                       # check if the target is found
        print("Letter found at index: ", index)  # if so, print the index
        # break 
else:
    print('Letter',target,'not found in',river)
        
