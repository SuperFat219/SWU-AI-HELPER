def find_max_min_mileage(mileage_list, max_mileage, min_mileage):
    """Make a list of cars with max and min mileage: list of car tuples."""
    max_mileage_list = []
    min_mileage_list = []

    # 5. find max and min mileage cars; append them to the appropriate list
    #    car_tuple[0] is item 0 of the tuple which is the mileage
    for car_tuple in mileage_list:
        if car_tuple[0] == max_mileage:
            max_mileage_list.append(car_tuple)
        if car_tuple[0] == min_mileage:
            min_mileage_list.append(car_tuple)

    return max_mileage_list, min_mileage_list
