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
