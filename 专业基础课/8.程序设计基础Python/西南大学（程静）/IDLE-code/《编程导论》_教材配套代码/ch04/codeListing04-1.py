# Our implementation of the find function. Prints the index where 
# the target is found; a failure message, if it isn't found. 
# This version only searches for a single character.

river = 'Mississippi'
target = input('Input a character to find: ')
for index in range(len(river)):         # for each index
    if river[index] == target:          # check if the target is found
        print("Letter found at index: ", index)  # if so, print the index
        break                           # stop searching
else:
    print('Letter',target,'not found in',river)
        
