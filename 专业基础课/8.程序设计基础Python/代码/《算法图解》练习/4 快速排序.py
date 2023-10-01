#分而治之（D&C）
def sum2(list):
    sum=0
    len=0
    while list:
        a=list.pop()
        sum+=a
        len+=1
    return sum,len

print(sum2([1,3,5,22]))

#快速排序
def quicksort(list):
    if len(list)<=1:
        return list
    else:
        pivot=list[0]
        less=[i for i in list[1:] if i<=pivot]
        bigger=[i for i in list[1:] if i>=pivot]
        return quicksort(less)+[pivot]+quicksort(bigger)
print(quicksort([1,34,234,35,546]))

#合并排序(升序)
def merge(list1,list2):
    final_list=[]
    a=b=0
    while a<len(list1) and b<len(list2):
        if list1[a]<list2[b]:
            final_list.append(list1[a])
            a+=1
        else:
            final_list.append(list2[b])
            b+=1
    if a==len(list1):
        for i in list2[b:]:
            final_list.append(i)
    else:
        for i in list1[a:]:
            final_list.append(i)
    return final_list
def merge_sort(list):
    if len(list)<=1:
        return list
    else:
        middle=len(list)//2
        left=merge_sort(list[:middle])
        right=merge_sort(list[middle:])
        return merge(left,right)
print(merge_sort([6,7,3,8,9,1,3,5,0]))