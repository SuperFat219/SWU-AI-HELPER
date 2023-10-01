# naming protocol:
#    1. names are strings
#    2. counts and indexes are ints

def make_data_set(file_name):  # file_name is a string
    '''Read file file_name (str); return list of tuples in format:
       id, diagnosis, 9 attributes.'''
    input_set_list = []

# open file. Fix the error checking
    input_file = open(file_name)

    for line_str in input_file:
        line_str = line_str.strip()  # strip off end-of-line character " \n"
        # if a '?' in the patient data, skip that patient
        if '?' in line_str:
            continue
        id_str,a1,a2,a3,a4,a5,a6,a7,a8,a9,diagnosis_str = line_str.split(',')
        if diagnosis_str == '4': # diagnosis is "malignant"
            diagnosis_str = 'm'
        else:
            diagnosis_str = 'b'  # diagnosis is "benign"
        patient_tuple=id_str,diagnosis_str,int(a1),int(a2),int(a3),int(a4),\
            int(a5),int(a6),int(a7),int(a8),int(a9)
        input_set_list.append(patient_tuple)
    return input_set_list


def sum_lists(list1,list2):
    """Element-by-element sums of two lists of 9 items."""
    sums_list = []
    for index in range(9):
        sums_list.append(list1[index]+list2[index])
    return sums_list

def make_averages(sums_list,total_int):
    """Convert each list element into an average by dividing by the total."""
    averages_list = []
    for value_int in sums_list:
        averages_list.append(value_int/total_int)
    return averages_list


def train_classifier(training_set_list):
    """Build a classifier using the training set."""
    benign_sums_list=[0]*9    # list of sums of benign attributes
    benign_count=0            # count of benign patients
    malignant_sums_list=[0]*9 # list of sums of malignant attributes
    malignant_count=0         # count of malignant patients

    for patient_tuple in training_set_list:
        if patient_tuple[1]=='b':   # if benign diagnosis
            # add benign attributes to benign total
            benign_sums_list=sum_lists(benign_sums_list,patient_tuple[2:])
            benign_count += 1
        else:                       # else malignant diagnosis
            # add malignant attributes to malignant total
            malignant_sums_list=sum_lists(malignant_sums_list,patient_tuple[2:])
            malignant_count += 1

    # find averages of each set of benign or malignant attributes
    benign_averages_list=make_averages(benign_sums_list,benign_count)
    malignant_averages_list=make_averages(malignant_sums_list,malignant_count)

    # separator values for each attribute averages benign and malignant
    classifier_list=make_averages(sum_lists(benign_averages_list,malignant_averages_list),2)

    return classifier_list

def classify_test_set(test_set_list, classifier_list):
    '''Given test set and classifier, classisfy each patient in test set;
       return list of result tuples: (id,benign_count,malignant_count,diagnosis)'''
    result_list = []
    # for each patient
    for patient_tuple in test_set_list:
        benign_count = 0
        malignant_count = 0
        id_str, diagnosis_str = patient_tuple[:2]
        # for each attribute of the patient,
        for index in range(9):
            # if actual patient attribute is greater than separator value
            #      "+2" skips id and diagnosis in list
            if patient_tuple[index+2] > classifier_list[index]:
                malignant_count += 1
            else:
                benign_count += 1
        result_tuple = (id_str,benign_count,malignant_count,diagnosis_str)
        result_list.append(result_tuple)
    return result_list

def report_results(result_list):
    '''Check results and report count of inaccurate classifications.'''
    total_count=0
    inaccurate_count = 0
    for result_tuple in result_list:
        benign_count, malignant_count, diagnosis_str = result_tuple[1:4]
        total_count += 1
        if (benign_count > malignant_count) and (diagnosis_str == 'm'):
            # oops! wrong classification
            inaccurate_count += 1
        elif diagnosis_str == 'b':  # and (benign_count < malignant_count)
            # oops! wrong classification
            inaccurate_count += 1
    print("Of ",total_count," patients, there were ",\
          inaccurate_count," inaccuracies")

def main():

    print("Reading in training data...")
    training_file = "training_data.txt"
    training_set_list = make_data_set(training_file)
    print("Done reading training data.\n")

    print("Training classifier...")    
    classifier_list = train_classifier(training_set_list)
    print("Done training classifier.\n")

    print("Reading in test data...")
    test_file = "test_data.txt"
    test_set_list = make_data_set(test_file)
    print("Done reading test data.\n")

    print("Classifying records...")
    result_list = classify_test_set(test_set_list, classifier_list)
    print("Done classifying.\n")

    report_results(result_list)

    print("Program finished.")
            

    
        
                
    
