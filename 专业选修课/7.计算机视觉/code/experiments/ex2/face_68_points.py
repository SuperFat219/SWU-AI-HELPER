import numpy as np
import cv2
import dlib
import os
import sys
import random

# dlib预测器
detector = dlib.get_frontal_face_detector()
predictor = dlib.shape_predictor('shape_predictor_68_face_landmarks.dat')

camera = cv2.VideoCapture(0)
while True:
    ok, img = camera.read()
    img_gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    rects = detector(img_gray, 0)
    for i in range(len(rects)):
        landmarks = np.matrix([[p.x, p.y] for p in predictor(img, rects[i]).parts()])
        for idx, point in enumerate(landmarks):
            pos = (point[0, 0], point[0, 1])
            cv2.circle(img, pos, 1, color=(0, 255, 0))
            font = cv2.FONT_HERSHEY_SIMPLEX
            cv2.putText(img, str(idx + 1), pos, font, 0.3, (0, 0, 255), 1, cv2.LINE_AA)
    cv2.imshow('video', img)
    k = cv2.waitKey(1)
    if k == 27:  # 按下ESC退出
        break
camera.release()
cv2.destroyAllWindows()

