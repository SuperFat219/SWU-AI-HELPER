import numpy
import matplotlib
import pylab
def bar_graph(word_count_dict):
    word_list = []
    for key,val in word_count_dict.items():
        if val>2 and len(key)>3:
            word_list.append((key,val))
            
    word_list.sort()
    key_list = [key for key,val in word_list]
    value_list = [val for key,val in word_list]
    bar_width=0.5
    x_values = numpy.arange(len(key_list))
    print("##:",x_values)
    print("$$:",x_values+bar_width/2.0)
    pylab.xticks(x_values+bar_width/2.0,key_list,rotation=45)
    pylab.bar(x_values,value_list,width=bar_width,color='r')
    pylab.show()
