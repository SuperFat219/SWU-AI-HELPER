import cv2
import dlib
import os
import random

output_dir = 'C:/Users/11038/Desktop/ex2/faces/Yan'
size = 480

if not os.path.exists(output_dir):
    os.makedirs(output_dir)

# adjust the brightness and contrast of captured image
def relight(img, light=1, bias=0):
    w = img.shape[1]
    h = img.shape[0]
    for i in range(0, w):
        for j in range(0, h):
            for c in range(3):
                tmp = int(img[j, i, c]*light + bias)
                if tmp > 255:
                    tmp = 255
                elif tmp < 0:
                    tmp = 0
                img[j, i, c] = tmp
    return img


detector = dlib.get_frontal_face_detector()
camera = cv2.VideoCapture(0)
index = 1
while True:
    if (index <= 20):
        print('Being processed picture %s' % index)
        success, img = camera.read()
        gray_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
        dets = detector(gray_img, 1)

        for i, d in enumerate(dets):
            x1 = d.top() if d.top() > 0 else 0
            y1 = d.bottom() if d.bottom() > 0 else 0
            x2 = d.left() if d.left() > 0 else 0
            y2 = d.right() if d.right() > 0 else 0

            face = img[x1:y1, x2:y2]
            face = relight(face, random.uniform(0.5, 1.5), random.randint(-50, 50))

            face = cv2.resize(face, (size, size))
            cv2.imwrite(output_dir+'/'+str(index)+'.jpg', face)
            index += 1
    else:
        camera.release()
        cv2.destroyAllWindows()
        break