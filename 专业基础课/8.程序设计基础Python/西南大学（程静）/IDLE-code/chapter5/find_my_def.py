def find_my(source,target):
   N = len(source)
   m = len(target)
   i = 0
   flag = True
   while(i<N-m+1):
        if(source[i:i+m] == target):
           flag = False
           break
        i = i+1

   if (flag):
        return -1
   else:
        return i
#---主程序-----
source_arg = "This is a cat title!"
target_arg = "111"
index = find_my(source_arg,target_arg)
print("位置：",index)
