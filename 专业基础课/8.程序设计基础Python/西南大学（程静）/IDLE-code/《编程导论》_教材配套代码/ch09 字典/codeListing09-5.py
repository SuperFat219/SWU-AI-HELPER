def main ():
    word_count_dict={}
    gba_file = open('gettysburg.txt','r')
    for line in gba_file:
        process_line(line, word_count_dict)
    print('Length of the dictionary:',len(word_count_dict))
    pretty_print(word_count_dict)
