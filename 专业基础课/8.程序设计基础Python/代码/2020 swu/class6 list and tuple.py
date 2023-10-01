#输入：一组学生考试成绩，以#结束
#功能：统计0-9，10-19,20-29，…,90-99,100。各个分数段的人数
#输出：打印出各个分数段人数
import matplotlib.pyplot as plt


def count1():
    global grade
    while True:
        grade = input('输入成绩：(输入#停止输入)')
        if grade == '#':
            break
        else:
            grade1 = float(grade)
            judgement(grade1)


def judgement(x):
    list1 = [59, 70, 80, 90, 100]
    for i in range(len(list1)):
        if x <= list1[i]:
            list2[i].append(x)
            break
        else:
            continue


#def count2():
#for i in range(len(list2)):
#print(len(list2[i]))
#print(list2[i])


def main():
    global list2
    list2 = [[], [], [], [], []]
    count1()
    #count2()
    draw()


def draw():
    # 这两行代码解决 plt 中文显示的问题
    plt.rcParams['font.sans-serif'] = ['SimHei']
    plt.rcParams['axes.unicode_minus'] = False

    grades = ('小于60', '60-70', '70-80', '80-90', '90-100')
    people_number = []
    for i in range(len(list2)):
        people_number.append(len(list2[i]))
    plt.bar(grades, people_number, color='b')
    plt.title('各分数段人数统计表')
    plt.show()


main()