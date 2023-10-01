# highest mileage data
# from http://www.fueleconomy.gov/FEG/download.shtml

def create_mileage_list(epa_file):
    """Create a list of cars and mileage from epa_file."""
    # 2a create a mileage list and initialize it to empty
    mileage_list = []

    for line in epa_file:                  # 2b. get each line from the file
        line_list = line.split(',')        # 2bI.  csv => split on comma
        mileage_list.append(line_list[9])  # 2bII. append highway mileage
    return mileage_list

#################################

# 1. open EPA data file
epa_file = open("epaData.csv", "r")
mileage_list = create_mileage_list(epa_file)

print(mileage_list)
