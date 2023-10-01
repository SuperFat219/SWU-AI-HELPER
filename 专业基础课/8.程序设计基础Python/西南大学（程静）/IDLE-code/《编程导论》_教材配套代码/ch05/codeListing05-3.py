# count poker hands

# 1. open the poker data file for reading
poker_file = open("poker-hand-testing.data",'r')

total_count_int = 0  # 2. create variable to hold the count -- initialized it

# 3. step through each line of the file
for line_str in poker_file:
    total_count_int = total_count_int + 1  # at each line increment the counter
    
print("Total hands in file:", total_count_int)
