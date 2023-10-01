"""
Date:2022.10.5
Laplacian算子边缘检测
"""

import cv2
img = cv2.imread(
    'F:/Swu_Documents/3.2022-2023 Semester/1.2022.9-2023.2/cv/code/4.jpg', 0)
cv2.imwrite("5_gray.png",img)
dst = cv2.Laplacian(img, cv2.CV_64F, ksize=3)
Laplacian = cv2.convertScaleAbs(dst)
cv2.imwrite("la.png",Laplacian)