def weighted_grade(score_list, weights_tuple=(0.3,0.3,0.4)):
    '''Expects 3 elements in score_list. Multiples each grade
    by its weight. Returns the sum.'''
    grade_float = \
        (score_list[0]*weights_tuple[0]) +\
        (score_list[1]*weights_tuple[1]) +\
        (score_list[2]*weights_tuple[2])
    return grade_float
