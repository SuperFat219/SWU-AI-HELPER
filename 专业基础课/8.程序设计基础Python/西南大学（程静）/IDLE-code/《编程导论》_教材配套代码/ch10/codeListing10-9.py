def make_averages(sums_list,total_int):
    """Convert each list element into an average by dividing by the total."""
    averages_list = []
    for value_int in sums_list:
        averages_list.append(value_int/total_int)
    return averages_list
