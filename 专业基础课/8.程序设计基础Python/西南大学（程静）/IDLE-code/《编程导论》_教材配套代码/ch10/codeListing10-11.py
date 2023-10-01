def classify_test_set_list(test_set_list, classifier_list):

# for each patient in the set    
    for patient_tuple in test_set_list:
     # for each attribute of the patient
        # if attribute is greater than the classifier corresponding attribute
        # increase the count of attributes indicating malignancy, otherwise
        # increase the count of attributes indicating benignity
     # create result tuple: (id,benign_count,malignant_count,diagnosis)
     # append the result tuple to the list of result tuples
# return the list of result tuples
