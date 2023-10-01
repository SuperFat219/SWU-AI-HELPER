"""
Date:2022.10.5
图像转灰度图、绘制累积直方图
"""

import cv2
import numpy as np
import matplotlib.pyplot as plt

# 彩色图像转灰度图
def img2gray(img):
    h, w, c = img.shape
    gray_img = np.zeros((h, w), dtype=np.uint8)
    for i in range(h):
        for j in range(w):
            gray_img[i, j] = round((img[i, j, 0]*30 + img[i, j, 1]*59 +
                                    img[i, j, 2]*11)/100)
    return gray_img

# 绘制灰度直方图
def histogram(img):
    gray_array = [0 for i in range(256)]
    h = img.shape[0]
    w = img.shape[1]
    gray_array = np.zeros(256)
    for i in range(h):
        for j in range(w):
            k = img[i,j]
            gray_array[k] = gray_array[k]+1
    for k in range(256):
        gray_array[k] = gray_array[k]/(h*w)
    return gray_array

# 绘制累计直方图
def histogram_sum(img):
    h = img.shape[0]
    w = img.shape[1]
    sum_gray_array = np.zeros(256)
    for i in range(h):
        for j in range(w):
            k = img[i,j]
            sum_gray_array[k] = sum_gray_array[k]+1
    for k in range(1,256):
        sum_gray_array[k] = sum_gray_array[k]+sum_gray_array[k-1]#累加
    for k in range(256):
        sum_gray_array[k] = sum_gray_array[k]/(h*w)
    return sum_gray_array

img = cv2.imread(
    'F:/Swu_Documents/3.2022-2023 Semester/1.2022.9-2023.2/cv/code/chapter_3/3.jpg')
gray_img = img2gray(img)
cv2.imwrite('F:/Swu_Documents/3.2022-2023 Semester/1.2022.9-2023.2/cv/code/chapter_3/3_gray.jpg',gray_img)
gray_array = histogram(gray_img)
plt.figure(figsize=(12,8))
plt.xlabel('pixel')
plt.bar(range(256), gray_array)
plt.savefig("histogram3.png")
sum_gray_array = histogram_sum(gray_img)
plt.figure(figsize=(12,8))
plt.xlabel('pixel')
plt.bar(range(256), sum_gray_array)
plt.savefig("histogram_sum3.png")