import cv2

fourcc = cv2.VideoWriter_fourcc(*'DIVX')
cap = cv2.VideoCapture('test.mp4')
fps = cap.get(cv2.CAP_PROP_FPS)
w = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH))
h = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT))
out2 = cv2.VideoWriter('output.avi', fourcc, fps, (w, h), 1)

while cap.isOpened():
    ret, frame = cap.read()
    query = cv2.waitKey(1)
    if query == ord('q'):
        break
    # cv2.imshow('video', frame)

    cv2.rectangle(frame, (600, 300), (400, 400), color=(255, 255, 0), thickness=1)
    cv2.imshow('frame', frame)
    # out2.write(frame)

cap.release()
# cv2.waitKey()
cv2.destroyAllWindows()
