river = "Mississippi"
target = input("Input a substring to find:")
a = len(target)
for index in range(len(river)):
    if river[index:index+a] == target:
        print("word is found at index:",index)
        break
else:
    print("word:",target,"is not found in",river)
