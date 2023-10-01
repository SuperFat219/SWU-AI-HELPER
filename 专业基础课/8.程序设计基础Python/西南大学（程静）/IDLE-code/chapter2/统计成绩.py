score_str = input("请输入学生的分数(0~100),以#结束：")
c_no = c_jige = c_zhong = c_liang = c_you = 0
while (score_str !="#"):
    score = float(score_str)
    if(0<score<60):
        c_no += 1
    elif(score<70):
        c_jige += 1
    elif(score<80):
        c_zhong += 1
    elif(score<90):
        c_liang += 1
    else:
        c_you += 1
    score_str = input("请输入学生的分数(0~100),以#结束：")

print("不及格:{}，及格:{}，中:{}，良:{}，优:{}".format(c_no,c_jige,c_zhong,c_liang,c_you))
