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
    classifier_list=make_averages(\
                    sum_lists(benign_averages_list,malignant_averages_list),2)

    return classifier_list
