# highest mileage data
# from http://www.fueleconomy.gov/FEG/download.shtml

def create_mileage_list(epa_file):
    """Create a list of cars and mileage from epa_file."""
    # 2a create a mileage list and initialize it to empty
    mileage_list = []

    for line in epa_file:                     # 2b. get each line from the file
        if line[0:5] == 'CLASS' or 'VAN' in line or 'PICKUP' in line: 
            continue                          # skip header, vans and pickups
        line_list = line.split(',')           # 2bI.  csv => split on comma
        # tuple (mileage, make, model)
        car_tuple = (int(line_list[9]), line_list[1], line_list[2])
        mileage_list.append(car_tuple)        # 2bII. append tuple
    return mileage_list

#################################

# 1. open EPA data file
epa_file = open("epaData.csv", "r")
mileage_list = create_mileage_list(epa_file)

# 3. find max and min mileage
max_mileage = max(mileage_list)
min_mileage = min(mileage_list)

print("Max and Min Mileage: ", max_mileage, min_mileage)
