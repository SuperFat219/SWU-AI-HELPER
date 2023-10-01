import string
def process_line(line, word_count_dict):
    '''Process the line to get lowercase words to add to the dictionary.'''
    line = line.strip()
    word_list = line.split()
    for word in word_list:
        # ignore the '--' that is in the file
        if word != '--':
            word = word.lower()
            word = word.strip()
            # get commas, periods and other punctuation out as well
            word = word.strip(string.punctuation)
            add_word(word, word_count_dict)


def add_word(word, word_count_dict):
    '''Update the word frequency: word is the key, frequency is the value.'''
    if word in word_count_dict:
        word_count_dict[word] += 1
    else:
        word_count_dict[word] = 1

def pretty_print(word_count_dict):
    '''Print nicely from highest to lowest frequency.'''
    # create a list of tuples, (value, key)
    # value_key_list = [(val,key) for key,val in d.items()]
    value_key_list=[]
    for key,val in word_count_dict.items():
        value_key_list.append((val,key))
    # sort method sorts on list's first element, the frequency. 
    # Reverse to get biggest first
    value_key_list.sort(reverse=True)
    # value_key_list = sorted([(v,k) for k,v in value_key_list.items()], reverse=True)
    print('{:11s}{:11s}'.format('Word', 'Count'))
    print('_'*21)
    for val,key in value_key_list:
        print('{:12s}  {:<3d}'.format(key,val))

def bar_graph(word_count_dict):
    '''bar graph of word-frequency, xaxis labeled with words'''
    # collect key and value list for plotting
    word_list = []
    for key,val in word_count_dict.items():
        if val>2 and len(key)>3:
            word_list.append((key,val))
    word_list.sort()
    key_list = [key for key,val in word_list]
    value_list = [val for key,val in word_list]
    # get ticks as the keys/words
    bar_width=0.5
    x_values = numpy.arange(len(key_list))
    pylab.xticks(x_values+bar_width/2.0,key_list,rotation=45)
    # create the bar graph
    pylab.bar(x_values,value_list,width=bar_width,color='r')
    pylab.show()

def main ():
    word_count_dict={}
    gba_file = open('gettysburg.txt','r')
    for line in gba_file:
        process_line(line, word_count_dict)
    print('Length of the dictionary:',len(word_count_dict))
    pretty_print(word_count_dict)

main()
