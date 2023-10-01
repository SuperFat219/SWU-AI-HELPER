string = "This is a turtle!" #N=17
target = 'b'
'''
for i in range(len(string)): #i=0,1,...,16
    if (string[i]==target):
        print(i)
        break

if(i==len(string)):
     print("{:s} is not in {:s}".format(target,string))
else:
    print("the index of {:s} is:{:5d}".format(target,i))
'''
i=0
while(i<len(string)):
    if(string[i] == target):
        print(i)
        #break
    i = i+1


if(i == len(string)):
     print("{:s} is not in \"{:s}\"".format(target, string))
else:
    print("the index of {:s} is:{:5d}".format(target, i))
