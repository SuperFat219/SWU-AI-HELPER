# highest mileage data
# from http://www.fueleconomy.gov/FEG/download.shtml

# 1. open EPA data file
epa_file = open("epaData.csv", "r")

for line in epa_file:      # get each line one at a time from the file
    if 'FERRARI' in line:  # if 'FERRARI' is anywhere in the line print it
        print(line[:75])   # only print first 75 characters
