#完成矩阵的输入，输出，矩阵的求和，求差，转置，矩阵的乘法
def matrix_input():
    matrix = []
    rows = int(input("输入行数：  "))
    columns = int(input("输入列数：  "))
    for i in range(rows):
        row = []
        for x in range(columns):
            while True:
                b = input('输入矩阵元素（按行输入）： ')
                if input_judgement(b) == True:
                    b = float(b)
                    row.append(b)
                    break
        matrix.append(row)
    #print(matrix)
    return matrix

def matrix_output(list1):
    for i in range(len(list1)):
        for j in range(len(list1[0])):
            print(list1[i][j],end=' ')
        print()

def input_judgement(x):
    if x.isdigit() == False:
        print('无效字符，请重新输入： ')
        return False
    return True


def row_count(list):
    row_length = len(list)
    return row_length

def column_count(list):
    column_length = len(list[0])
    return column_length

def judgement(list1, list2):
    #判断同型矩阵
    if (row_count(list1) != row_count(list2)) or (column_count(list1) !=
                                                  column_count(list2)):
        print('这两个矩阵不是同型矩阵，无法求和差！')
        return False
    return True

def matrix_sum(list1, list2):
    #同型矩阵才可以求和
    if judgement(list1, list2) == True:
        new_matrix = []
        new_row_length = row_count(list1)
        new_column_length = column_count(list1)
        for i in range(new_row_length):
            new_row = []
            for x in range(new_column_length):
                num1 = list1[i][x]
                num2 = list2[i][x]
                num = float(num1) + float(num2)
                new_row.append(num)
            new_matrix.append(new_row)
        print('两个矩阵的和为' )
        matrix_output(new_matrix)
        matrix_minus(list1, list2)

def matrix_minus(list1, list2):
    new_matrix = []
    new_row_length = row_count(list1)
    new_column_length = column_count(list1)
    for i in range(new_row_length):
        new_row = []
        for x in range(new_column_length):
            num1 = list1[i][x]
            num2 = list2[i][x]
            num = float(num1) - float(num2)
            new_row.append(num)
        new_matrix.append(new_row)
    print('两个矩阵的差为')
    matrix_output(new_matrix)


def matrix_transpose():
    print('输入要进行转置的矩阵')
    list1 = matrix_input()
    print('进行转置的矩阵为', list1)
    transpose_matrix = []
    new_row_length = column_count(list1)
    new_column_length = row_count(list1)
    for i in range(new_row_length):
        new_row = []
        for x in range(new_column_length):
            new_row.append(list1[x][i])
        transpose_matrix.append(new_row)
    print('转置后的矩阵为')
    matrix_output(transpose_matrix)
    return transpose_matrix


def multiply_judgement(list1, list2):
    if column_count(list1) != row_count(list2):
        print('这两个矩阵不能相乘')
        return False
    return True

def matrix_multiply(list1, list2):
    if multiply_judgement(list1, list2) == True:
        new_matrix = []
        for i in range(row_count(list1)):
            new_row = []
            for x in range(column_count(list2)):
                s = 0
                for j in range(column_count(list1)):
                    a = (list1[i][j]) * (list2[j][x])
                    s += a
                new_row.append(s)
            new_matrix.append(new_row)
        print('两个矩阵的乘积为')
        matrix_output(new_matrix)

def main():
    matrix1 = matrix_input()
    print('输入的第一个矩阵为')
    matrix_output(matrix1)
    matrix2 = matrix_input()
    print('输入的第二个矩阵为')
    matrix_output(matrix2)
    matrix_sum(matrix1, matrix2)
    matrix_multiply(matrix1, matrix2)
    matrix_transpose()

main()
