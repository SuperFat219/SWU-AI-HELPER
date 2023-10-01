def sum_lists(list1,list2):
    """Element-by-element sums of two lists of 9 items."""
    sums_list = []
    for index in range(9):
        sums_list.append(list1[index]+list2[index])
    return sums_list
