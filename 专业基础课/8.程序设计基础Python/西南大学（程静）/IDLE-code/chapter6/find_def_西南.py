'''
def find_by_myself(source,target):
    N = len(source)
    m = len(target)
    flag = True
    i=0
    while(i<N-m+1):
        if (source[i:i+m] == target):
            #position = i
            flag = False
            break
        i=i+1
    if (flag):
        position = -1
    else:
        position = i
    return position
'''

def find_by_myself(source,target):
    N = len(source)
    m = len(target)
    i = 0
    while(i<N-m+1):
        if(source[i:i+m] == target):
            return i
        i = i+1
    return -1

#---main-------------------
file_obj = open("西南大学.txt","r")
source_str = ""
for line in file_obj:
    source_str = source_str+line
print(source_str)
#调用find_by_myself---------
target_str = "swu"
index = find_by_myself(source_str,target_str)
print("\n\n",index)
