# highest mileage data
# from http://www.fueleconomy.gov/FEG/download.shtml

def create_mileage_list(epa_file):
    """Create a list of cars and mileage from epa_file."""
    # 2a create a mileage list and initialize it to empty
    mileage_list = []

    for line in epa_file:              # 2b. get each line from the file
        if line[0:5] == 'CLASS' or\
                 'VAN' in line or\
                 'PICKUP' in line:   
            continue                 # skip pickups, vans and the header line
        line_list = line.split(',')           # 2bI. csv => split on comma
        # create tuple: (mileage, make, model)
        car_tuple = (int(line_list[9]), line_list[1], line_list[2])
        mileage_list.append(car_tuple)        # 2bII. append tuple
    return mileage_list

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

##############################################
# 1. open EPA data file
epa_file = open("epaData.csv", "r")

print("EPA Car Mileage")
print() # blank line

# 2a create a mileage list
mileage_list = create_mileage_list(epa_file)

# 3. find max and min mileage
#    mileage_list is a list of tuples (mileage, make, model)
#    max(mileage_list)[0] finds the max mileage tuple and extracts the mileage
max_mileage = max(mileage_list)[0]
min_mileage = min(mileage_list)[0]

print("Max and Min Mileage: ", max_mileage, min_mileage)
print()  # print blank line

#4. create a list of all cars with max and min mileage: list of car tuples
max_mileage_list, min_mileage_list = \
                find_max_min_mileage(mileage_list,max_mileage,min_mileage)

print("Maximum Mileage Cars:")
for car_tuple in max_mileage_list:
    print("  ", car_tuple[1], car_tuple[2])
print("Minimum Mileage Cars: ")
for car_tuple in min_mileage_list:
    print("  ", car_tuple[1], car_tuple[2])
