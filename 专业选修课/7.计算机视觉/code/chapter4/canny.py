"""
Date:2022.10.5
Canny算子边缘检测
"""

import cv2
img = cv2.imread(
    'F:/Swu_Documents/3.2022-2023 Semester/1.2022.9-2023.2/cv/code/5.jpg', 0)
edges = cv2.Canny(image=img, threshold1=32, threshold2=128)
cv2.imwrite("canny5.png", edges)
