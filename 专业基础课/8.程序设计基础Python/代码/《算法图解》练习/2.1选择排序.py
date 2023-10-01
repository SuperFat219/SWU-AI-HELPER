def select_sort(list):
    sort_list=[]
    while list:
        number=find_max(list)
        sort_list.append(number)
        list.remove(number)
    return sort_list
def find_max(list):
    max_number=0
    for i in list:
        if i>max_number:
            max_number=i
    return max_number
#print(find_max([1,3,5,44,2]))
print(select_sort([9,3,25,3,6]))