import csv
workbook_file = open('Workbook1.csv','r')
workbook_reader = csv.reader(workbook_file)

sheet_list = []
for row in workbook_reader:
    sheet_list.append(row)
workbook_file.close()

sheet_list[3][3] = '100.00'   # give Irving a break, 100 on the final

# update Irving's average
sum_float = 0.0
for field_str in sheet_list[3][1:-1]:   # fields 1, 2 and 3: the grades
    sum_float += float(field_str)
avg_float = sum_float/3
# we have to write a string, convert to two decimal places using str formatting
sheet_list[3][4] = '{:.2f}'.format(avg_float)

# list comp shortcut for update Irving's average
# sheet_list[3][4]='{:.2f}'.format(sum([float(field_str)\
#                                for field_str in sheet_list[3][1:-1]])/3)

# update the overall grade average
sum_float = 0.0
for row in sheet_list[1:-2]:       # rows 1,2,3,4: the student rows
    sum_float += float(row[-1])    # the average of that row
grade_average_float = sum_float/4
# we have to write a string, convert to two decimal places using str formatting
sheet_list[-1][-1] = '{:.2f}'.format(grade_average_float)

# list comp shortcut for update overall average
# sheet_list[-1][-1] = '{:.2f}'.format(sum([float(row[-1])\
#                                        for row in sheet_list[1:-2]])/4)

newbook_file = open('NewWorkbook1.csv','w')
newbook_writer = csv.writer(newbook_file)
for row in sheet_list:
    newbook_writer.writerow(row)
newbook_file.close()
