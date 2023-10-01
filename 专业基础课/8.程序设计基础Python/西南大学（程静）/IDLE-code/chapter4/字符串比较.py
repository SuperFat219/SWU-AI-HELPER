str_1 = "ABCDefghijk"
str_2 = "ABcDefg"

len_1 = len(str_1)
len_2 = len(str_2)
N = min(len_1,len_2)
i = 0
while(i<N-1):
    if (str_1[i] == str_2[i]):
        i = i+1
    else:
        break

if (str_1[i] == str_2[i]):
    if (len_1 == len_2):
       print("Two strings are equal!")
    elif (len_1>len_2):
        print("The string_1 is bigger!")
    else:
        print("The string_2 is bigger!")
elif (str_1[i]>str_2[i]):
    print("The string_1 is bigger!")
else:
    print("The string_2 is bigger!")