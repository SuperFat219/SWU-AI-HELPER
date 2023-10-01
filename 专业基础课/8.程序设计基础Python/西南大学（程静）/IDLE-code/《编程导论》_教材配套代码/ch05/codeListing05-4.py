# count poker hands

# 1. open the poker data file for reading
poker_file = open("poker-hand-testing.data",'r')

total_count_int = 0  # 2. create and initialize variable to hold the total count
pair_count_int = 0   #    create and initialize variable to hold pair count

# 3. Loop through each line of the file
for line_str in poker_file:
    total_count_int = total_count_int + 1    # (a). add one total for each hand 

    fields = line_str.split(',')             # (b). split on a comma      
    hand_rank_str = fields[-1]               #      and get the last field
    hand_rank_int = int(hand_rank_str)       

    if hand_rank_int == 1:                   #(c) if handRank is 1 (it is a pair)
        pair_count_int = pair_count_int + 1  #    add one to pair count
    
print("Total hands in file: ", total_count_int)  # 4. print the values
print("Count of pair hands: ", pair_count_int)
