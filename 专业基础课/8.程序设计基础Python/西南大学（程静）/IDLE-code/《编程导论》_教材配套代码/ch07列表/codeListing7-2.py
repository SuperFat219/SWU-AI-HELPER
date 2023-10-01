# Anagram test

def are_anagrams(word1, word2):
    """Return True, if words are anagrams."""
    #2. Sort the characters of the words
    word1_sorted = sorted(word1)      # sorted returns a sorted list
    word2_sorted = sorted(word2)

    #3. Check that the sorted words are identical.
    if word1_sorted == word2_sorted:  # compare sorted lists 
        return True
    else:
        return False

print("Anagram Test")

# 1. Input two words.
two_words = input("Enter two space separated words: ")
two_word_list = two_words.split() # split the input string into a list of words
word1 = two_word_list[0]          # extract first word
word2 = two_word_list[1]          # extract second word

if are_anagrams(word1, word2):    # function returned True or False 
    print("The words are anagrams.")
else:
    print("The words are not anagrams.")

