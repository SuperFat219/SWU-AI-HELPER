# Features extraction from images and save into features_all.csv

import cv2
import os
import dlib
from skimage import io
import csv
import numpy as np

faces_dir_path = "C:/Users/11038/Desktop/ex2/faces/"

detector = dlib.get_frontal_face_detector()
predictor = dlib.shape_predictor("shape_predictor_68_face_landmarks.dat")
face_rec_model = dlib.face_recognition_model_v1("dlib_face_recognition_resnet_model_v1.dat")

# extract features of critical points for single image
def extract_128d_features(path_img):
    img_rd = io.imread(path_img)
    img_gray = cv2.cvtColor(img_rd, cv2.COLOR_BGR2RGB)
    faces = detector(img_gray, 1)

    if len(faces) != 0:
        shape = predictor(img_gray, faces[0])
        face_descriptor = face_rec_model.compute_face_descriptor(img_gray, shape)
    else:
        face_descriptor = 0
    return face_descriptor

# get mean features for single person
def get_mean_features_personX(path_faces_personX):
    features_list_personX = []
    photos_list = os.listdir(path_faces_personX)
    if photos_list:
        for i in range(len(photos_list)):
            features_128d = extract_128d_features(path_faces_personX + "/" + photos_list[i])
            if features_128d == 0:
                continue
            else:
                features_list_personX.append(features_128d)
    else:
        raise Exception("Warning: No images in {}".format(path_faces_personX))

    # N x 128D -> 1 x 128D
    if features_list_personX:
        features_mean_personX = np.array(features_list_personX).mean(axis=0)
    else:
        features_mean_personX = 0

    return features_mean_personX


people = os.listdir(faces_dir_path)
people.sort()

with open("C:/Users/11038/Desktop/ex2/features/faces_features_dataset.csv", "w", newline="") as csvfile:
    writer = csv.writer(csvfile)
    for person in people:
        print("##### " + person + " #####")
        # Get the mean/average features of face/personX, it will be a list with a length of 128D
        features_mean_personX = get_mean_features_personX(faces_dir_path + person)
        writer.writerow(features_mean_personX)
    print("Save all the features of {} faces.".format(len(people)))
