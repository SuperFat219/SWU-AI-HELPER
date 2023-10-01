import csv
work_file = open("test_csv.csv", "r")
work_reader = csv.reader(work_file)

sheet_list = []
for row in work_reader:
    sheet_list.append(row)
work_file.close()
print("1:::::",sheet_list)
print()
sheet_list[3][3] = '100.00'
sum_float = 0.0
for field_str in sheet_list[3][1:-1]:
    sum_float += float(field_str)
ave_float = sum_float/3

sheet_list[3][4] = '{:.2f}'.format(ave_float)
print("2::::",sheet_list)
print()

sum_float = 0.0
for row in sheet_list[1:-2]:
    print(row)
    sum_float += float(row[-1])
grade_average_float = sum_float/4
print("ave of grade is:",grade_average_float)
sheet_list[-1][-1] = '{:.2f}'.format(grade_average_float)
print()
print("3:::::",sheet_list)

newbook_file = open("revised_csv.csv", "w")
newbook_writer = csv.writer(newbook_file)
for row in sheet_list:
    newbook_writer.writerow(row)
newbook_file.close()
