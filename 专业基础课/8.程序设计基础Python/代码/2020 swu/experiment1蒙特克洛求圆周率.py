#蒙特卡洛求圆周率

import numpy as np


def monte_carlo(total_points):
    """ 圆中点的个数除以总点数即为圆周率
    Args:
         total_points: 生成点总个数
    Returns:
         pi,以list形式返回所有的x和y点
    """
    # 1：定义圆中点个数的计数器与点容器
    num_circle_points = 0
    x_list = []
    y_list = []

    # 2：生成total_points个随机点
    for i in range(total_points):
        # 3：在长宽均为1的矩形内生成随机点（x，y）
        rand_x = np.random.uniform(0, 1, size=1)
        rand_y = np.random.uniform(0, 1, size=1)
        x_list.append(rand_x)
        y_list.append(rand_y)
        # 4：判断随机点是否在圆内，如果在则圆中点个数计数器加1
        if np.add(rand_x**2, rand_y**2) <= 1:
            num_circle_points += 1

    # 5：根据圆中点个数与总点数的比值，即得到圆周率
    pi = 4 * float(num_circle_points) / float(total_points)
    print(pi)
    return pi, x_list, y_list


monte_carlo(1000)
'''
'''
import turtle
import random
turtle.penup()
turtle.goto(-250, 250)
turtle.pendown()
turtle.begin_fill()
turtle.fillcolor('#069')
for x in range(4):
    turtle.forward(500)
    turtle.right(90)
turtle.end_fill()
turtle.hideturtle()
turtle.forward(250)
turtle.circle(-250)
turtle.color('yellow')
n = 0
turtle.speed(0)
for i in range(1000):
    a = random.randint(-250, 250)
    b = random.randint(-250, 250)
    turtle.penup()
    turtle.goto(a, b)
    turtle.pensize(10)
    turtle.pendown()
    turtle.goto(a, b)
    if a**2 + b**2 < 250**2:
        n += 1
print(n / 1000 * 4)
