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
