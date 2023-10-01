# Gettysburg address analysis
# count words, unique words

def make_word_list(a_file):
    """Create a list of words from the file."""
    word_list = []      # list of speech words: initialized to be empty

    for line_str in a_file:           # read file line by line
        line_list = line_str.split()  # split each line into a list of words
        for word in line_list:        # get words one at a time from list
            if word != "--":          # if the word is not "--"
                word_list.append(word)   # add the word to the speech list
    return word_list

################################
                
gba_file = open("gettysburg.txt", "r")
speech_list = make_word_list(gba_file)

# print the speech and its lengths
print(speech_list)          
print("Speech Length: ", len(speech_list))
