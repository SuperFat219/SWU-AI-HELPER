# Word puzzle driver
# Assumes word_list.txt file of one word per line

def get_word_list():
    """Return a list of words from a word_list.txt file."""
    data_file = open("word_list.txt","r")
    word_list = []          # start with an empty word list
    for word in data_file:  # for every word (line) in the file
        # strip off end-of-line characters and make each word lowercase
        # then append the word to the word_list
        word_list.append(word.strip().lower())
    return word_list

def puzzle(word_list):
    """Puzzle solution goes here."""
    pass     # filler that does nothing except put something in the suite

word_list = get_word_list()
puzzle(word_list)
