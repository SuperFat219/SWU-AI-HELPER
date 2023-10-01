data_file = open("dictionary.txt","r")

def clean_word(word):
   """Return word in l owercase stri p ped of whitespace . """
   return word.strip().lower()

def get_vowels_in_word(word):
    """Return vowels in stri ng word−−include re pe ats . """
    vowel_str = "aeiou"
    vowels_in_word = ""
    for char in word:
       if char in vowel_str:
           vowels_in_word += char
    return vowels_in_word

# main program
print("Find words containing vowels 'aeiou' in that order:")
for word in data_file: # for each word in the file
    word = clean_word(word) # clean the word
    str_list = word.split(" ")
    word = str_list[0]
    if len(word) <= 6: # if word is too small , skip it
       continue
    vowel_str = get_vowels_in_word(word) # get vowels in word
    if vowel_str == 'aeiou': # check if you have exactly all vowels in order
       print(word)

    
