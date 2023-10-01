"""
Date:2022.10.5
Sobel算子边缘检测
"""

import cv2
img = cv2.imread(
    'F:/Swu_Documents/3.2022-2023 Semester/1.2022.9-2023.2/cv/code/5.jpg', 0)
cv2.imwrite('blur.png', img)
sobelx = cv2.Sobel(src=img, ddepth=cv2.CV_64F, dx=1, dy=0, ksize=3)
sobely = cv2.Sobel(src=img, ddepth=cv2.CV_64F, dx=0, dy=1, ksize=3)
sobelxy = cv2.Sobel(src=img, ddepth=cv2.CV_64F, dx=1, dy=1, ksize=5)
cv2.imwrite('x.png', sobelx)
cv2.imwrite('y.png', sobely)
cv2.imwrite('xy.png', sobelxy)
