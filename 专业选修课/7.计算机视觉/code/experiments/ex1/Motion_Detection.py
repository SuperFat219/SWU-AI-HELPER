import numpy as np
import cv2 as cv
cap = cv.VideoCapture("flapping_demo_video.avi")
fourcc = cv.VideoWriter_fourcc(*'DIVX')
out = cv.VideoWriter('output.avi', fourcc, 20.0, (640, 368))
# fourcc2 = cv.VideoWriter_fourcc(*'DIVX')
out2 = cv.VideoWriter('output_crop.avi', fourcc, 20.0, (640, 368), 0)
ret, frame = cap.read()
gray = cv.cvtColor(frame, cv.COLOR_BGR2GRAY)
roi = cv.selectROI(windowName="roi", img=gray,
                   showCrosshair=True, fromCenter=False)
x, y, w, h = roi
cap = cv.VideoCapture("flapping_demo_video.avi")
last_k = 0
k_flag = False
frame_count = 0
state = 0
word_x = 190
word_y = 150
while(cap.isOpened()):
    ret, frame = cap.read()
    if ret == True:
        # process frame by frame
        gray = cv.cvtColor(frame, cv.COLOR_BGR2GRAY)
        cropped = gray[y:y+h, x:x+w]  # transform to gray image
        cropped = cv.resize(cropped, (640, 368))  # crop the roi region

        # pre-processing
        cropped = cv.blur(cropped, (5, 5))
        kernel = cv.getStructuringElement(cv.MORPH_RECT, (9, 9))
        opened = cv.morphologyEx(cropped, cv.MORPH_OPEN, kernel)
        closed = cv.morphologyEx(opened, cv.MORPH_CLOSE, kernel)
        ret, binary = cv.threshold(closed, 128, 255, cv.THRESH_BINARY)
        contours, hierarchy = cv.findContours(
            binary, cv.RETR_TREE, cv.CHAIN_APPROX_SIMPLE)  # find the contours
        cv.drawContours(cropped, contours, -1, (0, 0, 255), 3)  # draw the contours
        if (frame_count % 3 == 0):
            edges = cv.Canny(cropped, 30, 50, (3, 3))  # extract the edges
            # motion detection
            lines = cv.HoughLinesP(
                edges, 1, np.pi/180, 100, minLineLength=100, maxLineGap=10)  # detect the lines
            if type(lines) == type(None):
                continue
            if (len(lines) != 0):
                for line in lines:
                    x1, y1, x2, y2 = line[0]
                    if ((x1 == x2) or (y1 == y2) or ((x2-x1)/(y2-y1) > 5)):
                        continue
                    k = (x2-x1)/(y2-y1)
                    if (k < 0):
                        continue
                    if (k_flag == True):
                        if (abs(k-last_k) < 0.1):
                            state = 0
                        else:
                            state = 1
                    cv.line(cropped, (x1, y1), (x2, y2), (255, 0, 0), 5)
                    if (k_flag == False):
                        last_k = (x2-x1)/(y2-y1)
                        k_flag = True
        if (state):
            cv.putText(cropped, 'running', (word_x, word_y),
                                cv.FONT_HERSHEY_SIMPLEX, 1, (255, 0, 0), 2)
            cv.putText(frame, 'running', (word_x, word_y),
                        cv.FONT_HERSHEY_SIMPLEX, 1, (55, 255, 155), 2)
        else:
            cv.putText(cropped, 'stopped', (word_x, word_y),
                                cv.FONT_HERSHEY_SIMPLEX, 1, (255, 0, 0), 2)
            cv.putText(frame, 'stopped', (word_x, word_y),
                        cv.FONT_HERSHEY_SIMPLEX, 1, (55, 255, 155), 2)
        cv.imshow("frame", cropped)
        out.write(frame)
        out2.write(cropped)
        # cv.imshow("frame", cropped)
        frame_count += 1
        if cv.waitKey(1) & 0xFF == ord('q'):
            break
    else:
        break
cap.release()
cv.destroyAllWindows()
