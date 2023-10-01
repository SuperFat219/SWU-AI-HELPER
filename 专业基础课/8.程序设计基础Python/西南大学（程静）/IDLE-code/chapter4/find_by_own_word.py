string = '''学校溯源于西南1906西南年建立年建立的西南'''

target = "西南"


N = len(string)
m = len(target)
print(N,m)
print()
index = 0
while(index<N-m+1):
    if (string[index:index+m] == target):
        print(index,end=" ")
        break
    index = index+1


#print("\nEnd of While")

if(index == N-m+1):
    print(-1)
