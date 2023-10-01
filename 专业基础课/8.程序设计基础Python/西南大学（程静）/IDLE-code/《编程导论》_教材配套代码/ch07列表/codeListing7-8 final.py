# Gettysburg address analysis
# count words, unique words

def make_word_list(a_file):
    """Create a list of words from the file."""
    word_list = []      # list of speech words: initialized to be empty

    for line_str in a_file:           # read file line by line
        line_list = line_str.split()  # split each line into a list of words
        for word in line_list:        # get words one at a time from list
            word = word.lower()       # make words lower case
            word = word.strip('.,')   # strip off commas and periods            
            if word != "--":          # if the word is not "--"
                word_list.append(word)   # add the word to the speech list
    return word_list

def make_unique(word_list):
    """Create a list of unique words."""
    unique_list = []  # list of unique words: initialized to be empty

    for word in word_list:        # get words one at a time from speech
        if word not in unique_list: # if word is not already in unique list,
            unique_list.append(word)# add word to unique list

    return unique_list


################################
                
gba_file = open("gettysburg.txt", "r")
speech_list = make_word_list(gba_file)
print("Speech Length: ", len(speech_list))
unique_list = make_unique(speech_list)
# print the speech and its lengths
print(unique_list)          
print("Unique Length: ", len(make_unique(unique_list)))

