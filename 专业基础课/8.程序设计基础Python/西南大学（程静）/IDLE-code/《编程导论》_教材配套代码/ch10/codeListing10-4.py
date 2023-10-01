def make_training_set(file_name):
    training_set_list = []
# open file
    training_file = open(file_name)
# read in a line of the file
    for line_str in training_file:
# parse the line into its 11 parts
        id_str,a1,a2,a3,a4,a5,a6,a7,a8,a9,diagnosis_str = line_str.split(',')
    
# create a new training_set element
        
# append to the end of the training_set list

# return the training set  
    return training_set_list
