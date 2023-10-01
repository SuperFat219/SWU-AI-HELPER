def classify_test_set_list(test_set_list, classifier_list):
    '''Given test set and classifier, classisfy each patient in test set;
       return list of result tuples: (id,benign_count,malignant_count,diagnosis)'''
    result_list = []
    # for each patient
    for patient_tuple in test_set_list:
        benign_count = 0
        malignant_count = 0
        id_str, diagnosis_str = patient_tuple[:2]
        # for each attribute of the patient
        for index in range(9):
            # if actual patient attribute is greater than separator value
            if patient_tuple[index] > classifier_list[index]:
                malignant_count += 1
            else:
                benign_count += 1
        result_tuple = (id_str,benign_count,malignant_count,diagnosis_str)
        result_list.append(result_tuple)
    return result_list

