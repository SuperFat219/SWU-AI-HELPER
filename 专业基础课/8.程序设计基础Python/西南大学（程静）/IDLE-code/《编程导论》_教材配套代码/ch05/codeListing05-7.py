# count poker hands

# 1. open the poker data file for reading
file_str = input("Enter a file name: ")
while True:   # loop until you break
    try:
        poker_file = open(file_str,'r')
        break                 # success! so move on to rest of program
    except IOError:
        print("Error opening file:",file_str)
        file_str = input("Enter a file name: ")

# all counts are ints, so count as a suffix is enough
total_count          = 0  
nothing_count        = 0
pair_count           = 0   
two_pair_count       = 0
three_of_a_kind_count= 0
straight_count       = 0
flush_count          = 0
full_house_count     = 0
four_of_a_kind_count = 0
straight_flush_count = 0
royal_flush_count    = 0

for line_str in poker_file:
    total_count = total_count + 1  
    fields = line_str.split(',')             # split on a comma      
    hand_rank_str = fields[-1]               # and get the last field
    try:
        hand_rank_int = int(hand_rank_str)
    except ValueError:
        continue         # bad line: quietly skip this line of the file
    if hand_rank_int == 1:
        pair_count = pair_count + 1
    elif hand_rank_int == 2:
        two_pair_count = two_pair_count + 1
    elif hand_rank_int == 3:
        three_of_a_kind_count = three_of_a_kind_count + 1
    elif hand_rank_int == 4:
        straight_count = straight_count + 1
    elif hand_rank_int == 5:
        flush_count = flush_count + 1
    elif hand_rank_int == 6:
        full_house_count = full_house_count + 1
    elif hand_rank_int == 7:
        four_of_a_kind_count = four_of_a_kind_count + 1
    elif hand_rank_int == 8:
        straight_flush_count = straight_flush_count + 1
    elif hand_rank_int == 9:
        royal_flush_count = royal_flush_count + 1
    else:
        nothing_count = nothing_count + 1
    
print("Total hands in file: ", total_count)
print("Hand counts by rank number: ", nothing_count, pair_count, two_pair_count, \
      three_of_a_kind_count, straight_count, flush_count, full_house_count, \
      four_of_a_kind_count, straight_flush_count,  royal_flush_count )

print("Probability:")
print(" of nothing:         {:>9.4%} ".format(        nothing_count/total_count))
print(" of one pair:        {:>9.4%} ".format(           pair_count/total_count))
print(" of two pairs:       {:>9.4%} ".format(       two_pair_count/total_count))
print(" of three of a kind: {:>9.4%} ".format(three_of_a_kind_count/total_count))
print(" of a straight:      {:>9.4%} ".format(       straight_count/total_count))
print(" of a flush:         {:>9.4%} ".format(          flush_count/total_count))
print(" of a full house:    {:>9.4%} ".format(     full_house_count/total_count))
print(" of four of a kind:  {:>9.4%} ".format( four_of_a_kind_count/total_count))
print(" of a straight flush:{:>9.4%} ".format( straight_flush_count/total_count))
print(" of a royal flush:   {:>9.4%} ".format(    royal_flush_count/total_count))
