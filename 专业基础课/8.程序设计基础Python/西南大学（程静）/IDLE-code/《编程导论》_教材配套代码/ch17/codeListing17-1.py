
def setup_grades (grade_dict, default_grade, *student_tuple, **special_dict):
    ''' Setup a grade dictionary. All students (variable number) get
        the default grade except for any keyword pairs provided
    '''
    for student in student_tuple:
        grade_dict[student] = default_grade

    for student,grade in special_dict.items():
        grade_dict[student] = grade


grade_dict = {}

setup_grades(grade_dict, 0, 'wanda', 'fred', 'irving', bill=100, rich=100)

for key,val in grade_dict.items():
    print('{:10} | {:5}'.format(key,val))
                      
