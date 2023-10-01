# Find a word with a single example of the vowels a, e, i, o, u in that order
vowel_str_c = "swu"

#vowel_str_c = "aeiou"

data_file = open("dictionary.txt", "r")

def clean_word(word):
    """Return word in lower case stripped of whitespace."""
    return word.strip().lower()

def get_vowels_in_word(word):
    """Return vowels in string word--include repeats."""
    
    vowels_in_word = ""
    for char in word:
        if char in vowel_str_c:
            vowels_in_word += char
    return vowels_in_word
           
# main program
print("Find words containing vowels 'aeiou' in that order:")
for word in data_file:      # for each word in the file
    word = clean_word(word) # clean the word
    if len(word) <= 6:      # if word is too small, skip it
        continue
    vowel_str = get_vowels_in_word(word)  # get vowels in word
    if vowel_str == vowel_str_c:              # check if you have exactly all vowels in order
        print(word)
  

                        
        
