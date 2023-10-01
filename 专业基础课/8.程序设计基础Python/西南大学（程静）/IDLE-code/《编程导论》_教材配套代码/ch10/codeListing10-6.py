def make_training_set(file_name):
    """Reads a training set from the specified file.
       return list of tuples in format: id, diagnosis, 9 attributes."""
    training_set_list = []

# open file. Fix the error checking
    training_file = open(file_name)

    for line_str in training_file:
        line_str = line_str.strip() # strip off end-of-line character " \n"
        id_str,a1,a2,a3,a4,a5,a6,a7,a8,a9,diagnosis_str = line_str.split(',')
        if diagnosis_str == '4': # diagnosis is "malignant"
            diagnosis_str = 'm'
        else:
            diagnosis_str = 'b'  # diagnosis is "benign"
        patient_tuple=id_str,diagnosis_str,int(a1),int(a2),int(a3),int(a4),\
            int(a5),int(a6),int(a7),int(a8),int(a9)
        training_set_list.append(patient_tuple)
    return training_set_list
