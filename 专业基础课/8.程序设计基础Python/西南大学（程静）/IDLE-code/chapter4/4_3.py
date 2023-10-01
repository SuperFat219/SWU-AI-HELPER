river = "Mississippi"
target = input("Input a character to find:")
flag = True
for index,letter in enumerate(river):
    if letter == target:
        print("Letter is found at index:",index)
        flag = False
if flag:
    print("Letter:",target,"is not found in ",river)
    
