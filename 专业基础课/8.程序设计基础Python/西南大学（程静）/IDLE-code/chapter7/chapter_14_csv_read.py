import csv
work_file = open('test_csv.csv','r')
##--方法1:直接读文本文件----------
'''print("###############")
sheet_ls_str = []
for a_line in work_file:
    print(a_line)
    sheet_ls_str.append(a_line)
print(sheet_ls_str)
print()
'''
##--方法2：csv.reader()-----------
work_file.seek(0)
sheet_ls = []
work_reader = csv.reader(work_file)
for row in work_reader:
    print(row)
    sheet_ls.append(row)

print()
print("@@@@@@@@@@@")
print(sheet_ls)

work_file.close()
