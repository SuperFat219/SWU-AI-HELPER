#一直输入文件名，直到能够正确打开文件为止
while True:
      file_str = input("Open what file:")
      try:
          input_file = open(file_str) # potential user error
          break
      except IOError:
          print("The file",file_str,"doesn't exist.")
          print("Please input a new file name again!")

#一直输入行数，直到输入正确行号为止
while True:
    try:
       find_line_str = input("Which line (integer):")
       find_line_int = int(find_line_str)
       break
    except ValueError:
        print("Line",find_line_str,"isn't a legal line number.")
        print("Please input a new line number again!")
        
    
    
line_count_int = 1
for line_str in input_file:
    if line_count_int == find_line_int:
        print("Line {} of file {} is: {}".format(find_line_int, file_str,line_str))
        break
    line_count_int += 1
else:
    print("Line {} of file {} not found".format(find_line_int, file_str))

input_file.close()
    
print("Program is End")



