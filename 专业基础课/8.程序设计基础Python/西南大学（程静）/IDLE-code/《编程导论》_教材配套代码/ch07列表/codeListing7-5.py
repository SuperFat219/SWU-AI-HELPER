# Gettysburg address analysis
# count words, unique words

def make_word_list(a_file):
    """Create a list of words from a file."""
    word_list = []      # 2. list of speech words: initialized to be empty

    for line_str in a_file:         # 3. read file line by line
        line_list= line_str.split() # 3a. split each line to a list of words
        word_list.extend(line_list)    # 3b. add words to list of speech words
    return word_list

################################

gba_file = open("gettysburg.txt", "r") # 1. open file for reading
speech_list = make_word_list(gba_file)

# 4. print the speech and its lengths
print(speech_list)          
print("Length: ", len(speech_list))

