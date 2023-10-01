def add_word(word, word_set):
    '''Add the word to the set. No word smaller than length 3.'''
    if len(word) > 3:
        word_set.add(word)
