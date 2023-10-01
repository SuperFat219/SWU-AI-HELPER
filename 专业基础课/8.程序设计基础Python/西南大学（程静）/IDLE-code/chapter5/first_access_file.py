in_file = open("E://上课//python//编程导论//IDLE-code//temp.txt","r") 
for line_str in in_file: 
     print(line_str,end='')
in_file.close()
print("Read from a file is closed!\n")


out_file = open("out.txt","w")
print("first line\n\n",file=out_file)
print("second line",file=out_file)
out_file.close()
print("Write to a file is closed!\n")
