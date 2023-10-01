# Count words in string

speech = "to be or not to be"
speech_list = speech.split()

word_count_dict = {}

for word in speech_list:
    if word in word_count_dict:
        word_count_dict[word] += 1
    else:
        word_count_dict[word] = 1

print(word_count_dict)
