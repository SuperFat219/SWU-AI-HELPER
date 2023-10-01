import string
def process_line(line, word_set):
    '''Process the line to get lowercase words to be added to the set.'''
    line = line.strip()
    word_list = line.split()
    for word in word_list:
        # ignore the '--' that is in the file
        if word != '--':
            word = word.strip()
            # get commas, periods and other punctuation out as well
            word = word.strip(string.punctuation)
            word = word.lower()
            add_word(word, word_set)
