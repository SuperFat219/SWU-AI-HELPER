import pickle

class Grades(object):
    ''' grades are a list of tuples: test,grade'''
    def __init__(self, name='', semester='FS12'):
        self.grades_dict = {}
        self.class_name = name
        self.semester = semester

    def add_name(self, name):
        self.grades_dict[name] = []

    def add_grade(self, name, grade):
        try:
            self.grades_dict[name].append(grade)
        except KeyError:
            print("Bad name, can't add grade")

    def __str__(self):
        ''' print in alphabetical order'''
        grade_list = list(self.grades_dict.items())
        grade_list.sort()
        result_str = ''
        result_str += 'Class:'+self.class_name+', Semester:'+self.semester+'\n'
        for student in grade_list:
            result_str += 'Student:{:10}, Grades:{}\n'.format(student[0],student[1])
        return result_str
