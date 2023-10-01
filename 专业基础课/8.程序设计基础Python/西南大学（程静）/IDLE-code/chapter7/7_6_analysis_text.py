def del_punctuate(a_file):
    b_file = open("gettysburg_no_punc.txt","w") 
    whole_str = ""
    for line_str in a_file:
        for i in ".,?:\"":
            line_str = line_str.replace(i,"")
        #print(line_str)
        whole_str = whole_str+line_str
    #print(whole_str)
    print(whole_str,file = b_file)
    b_file.close()
    return b_file
    
def make_word_list(a_file):
    word_list = [] 
    for line_str in a_file: # read file line by line
        line_list = line_str.split()
        for word in line_list:    # get words one at a time from list
            if word != "--":
                word_list.append(word) # add the word to the speech list
    return word_list

def make_unique(word_list):
    unique_list = [] 
    for word in word_list: 
        if word not in unique_list:
             unique_list.append(word)
    return unique_list
################################

gsb_file = open("gettysburg.txt", "r")

tem_file = del_punctuate(gsb_file)  #去除掉原文本中的标点符号

tem_file = open("gettysburg_no_punc.txt","r") #打开新生成的没有标点符号的临时文件
speech_list = make_word_list(tem_file)

# print the speech and its lengths
print(speech_list)
print("Speech Length: ", len(speech_list))
print("Unique Length: ", len(make_unique(speech_list)))
gsb_file.close()
tem_file.close()
