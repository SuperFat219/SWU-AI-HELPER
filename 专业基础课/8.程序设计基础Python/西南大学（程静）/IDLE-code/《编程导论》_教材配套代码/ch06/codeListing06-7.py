# Find a word with a single example of the vowels a, e, i, o, u in that order

data_file = open("dictionary.txt", "r")

def clean_word(word):
    """Return word in lower case stripped of whitespace."""
    return word.strip().lower()

# main program
for word in data_file:      # for each word in the file
    word = clean_word(word) # clean the word
    if len(word) <= 6:     # skip word if too small to have all vowels
        continue
    print(word)
