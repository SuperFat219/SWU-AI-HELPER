import os
import dlib
import time
import numpy as np
import cv2
import pandas as pd
from collections import deque

face_rec_model = dlib.face_recognition_model_v1(
    "dlib_face_recognition_resnet_model_v1.dat")


RGBs = [(255, 0, 0), (255, 255, 0), (0, 255, 0)]
font = cv2.FONT_HERSHEY_SIMPLEX

# compute the e-distance between two 128D features


def euclidean_distance(feature_1, feature_2):
    feature_1 = np.array(feature_1)
    feature_2 = np.array(feature_2)
    dist = np.sqrt(np.sum(np.square(feature_1 - feature_2)))
    return dist


faces_features_path = "C:/Users/11038/Desktop/ex2/features/faces_features_dataset.csv"
faces_features_csv = pd.read_csv(faces_features_path, header=None)

# the array to save the features of faces in the database
faces_features_dataset = []

for i in range(faces_features_csv.shape[0]):
    features_someone_arr = []
    for j in range(0, len(faces_features_csv.loc[i, :])):
        features_someone_arr.append(faces_features_csv.loc[i, :][j])
    faces_features_dataset.append(features_someone_arr)
print("Faces in Database:", len(faces_features_dataset))

center_points = []
for i in range(len(faces_features_dataset)):
    center_points.append(deque(maxlen=15))

detector = dlib.get_frontal_face_detector()
predictor = dlib.shape_predictor('shape_predictor_68_face_landmarks.dat')

fourcc = cv2.VideoWriter_fourcc(*'DIVX')
cap = cv2.VideoCapture(0)
out2 = cv2.VideoWriter('output.avi', fourcc, 30.0, (640,480), 1)

while cap.isOpened():
    flag, frame = cap.read()
    query = cv2.waitKey(1)
    img_gray = cv2.cvtColor(frame, cv2.COLOR_RGB2GRAY)
    faces = detector(img_gray, 0)  # get detected faces

    # the list to save the positions and names of current faces captured
    pos_namelist = []
    name_namelist = []

    # press 'q' to exit
    if query == ord('q'):
        break
    else:
        if len(faces) != 0:
            detected_faces_features = []
            for i in range(len(faces)):
                shape = predictor(frame, faces[i])  # feature extraction
                detected_faces_features.append(
                    face_rec_model.compute_face_descriptor(frame, shape))

            # traversal all the faces in the database
            for k in range(len(faces)):
                name_namelist.append("unknown")
                # the positions of faces captured
                pos_namelist.append(tuple([faces[k].left(), int(
                    faces[k].bottom() + (faces[k].bottom() - faces[k].top())/8)]))

                # for every faces detected, compare the faces in the database
                e_distance_list = []
                for i in range(len(faces_features_dataset)):
                    if str(faces_features_dataset[i][0]) != '0.0':
                        e_distance_tmp = euclidean_distance(
                            detected_faces_features[k], faces_features_dataset[i])
                        e_distance_list.append(e_distance_tmp)
                    else:
                        e_distance_list.append(255)

                # Find the one with minimum e distance
                similar_person_num = e_distance_list.index(
                    min(e_distance_list))

                if min(e_distance_list) < 0.4:
                    center_points[similar_person_num].append(
                        ((faces[k].left()+faces[k].right())/2, (faces[k].top()+faces[k].bottom())/2))
                    folder_name = 'C:/Users/11038/Desktop/ex2/faces/'
                    key_id = 0
                    names = os.listdir(folder_name)
                    for name in names:
                        if similar_person_num == key_id:
                            name_namelist[k] = name
                        key_id += 1

                    for i, d in enumerate(faces):
                        x1 = d.top() if d.top() > 0 else 0
                        y1 = d.bottom() if d.bottom() > 0 else 0
                        x2 = d.left() if d.left() > 0 else 0
                        y2 = d.right() if d.right() > 0 else 0
                        face = frame[x1:y1, x2:y2]
                        size = 256
                        face = cv2.resize(face, (size, size))
                        save_dir = "recordings/"
                        now_time = time.strftime(
                            "%Y-%m-%d-%H-%M-%S", time.localtime())
                        save_name = str(now_time)+str(name_namelist[k])+'.jpg'
                        save_path = save_dir + save_name
                        visitor_names = os.listdir(save_dir)
                        visitor_name = ''
                        for name in visitor_names:
                            # 名字切片到分钟数：2019-06-26-11-33-00yan.jpg
                            visitor_name = (name[0:16]+'-00'+name[18:])
                        visitor_save = (save_name[0:16]+'-00'+save_name[18:])
                        # 一分钟之内重复的人名不保存
                        if visitor_save != visitor_name:
                            cv2.imwrite(save_path, face)
                            print('Found people:' + save_dir +
                                  str(now_time) + str(name_namelist[k])+'.jpg')
                        else:
                            pass
                else:
                    for i, d in enumerate(faces):
                        x1 = d.top() if d.top() > 0 else 0
                        y1 = d.bottom() if d.bottom() > 0 else 0
                        x2 = d.left() if d.left() > 0 else 0
                        y2 = d.right() if d.right() > 0 else 0
                        face = frame[x1:y1, x2:y2]
                        size = 256
                        face = cv2.resize(face, (size, size))
                        path_visitors_save_dir = "recordings/"
                        now_time = time.strftime(
                            "%Y-%m-%d-%H-%M-%S", time.localtime())
                        save_path = path_visitors_save_dir + \
                            str(now_time)+'unknown.jpg'
                        cv2.imwrite(save_path, face)
                        print('Stranger:'+path_visitors_save_dir +
                              str(now_time)+'unknown.jpg')

                for kk, d in enumerate(faces):
                    cv2.rectangle(frame, tuple([d.left(), d.top()]), tuple(
                        [d.right(), d.bottom()]), (0, 255, 255), 2)
                    # print(center_points[similar_person_num])
                    for i in range(len(center_points[similar_person_num])):
                        cv2.circle(
                            frame, (int(center_points[similar_person_num][i][0]), int(center_points[similar_person_num][i][1])), 2, RGBs[similar_person_num], 2)

            # write names under rectangle
            for i in range(len(faces)):
                cv2.putText(
                    frame, name_namelist[i], pos_namelist[i], font, 0.8, RGBs[similar_person_num], 2, cv2.LINE_AA)

    print("Faces in camera now:", name_namelist)
    cv2.putText(frame, "Faces: " + str(len(faces)), (10, 50),
                font, 1, (255, 255, 0), 1, cv2.LINE_AA)
    cv2.imshow("camera", frame)
    out2.write(frame)

cap.release()
cv2.destroyAllWindows()
