% =========================================================================
% 名称：利用alaxnet对摄像头图像进行分类
% 日期：2022.10.23
% 作者：冀杰
% 版本：MATLAB 2022a
% 本m文件调用alaxnet深度学习网络，并对摄像头图像进行分类，观察结果
% 该程序用于《人工智能基础》课程第二章仿真案例
% =========================================================================
clc;                                                                       % 清除命令行窗口
clear all;                                                                 % 清除所有数据
camera = webcam;                                                           % 连接摄像头
net = googlenet;                                                             % 调用alaxnet网络
while true
    im = snapshot(camera);                                                 % Take a picture
    image(im);                                                             % Show the picture
    im = imresize(im,[224 224]);                                           % Resize the picture for alexnet
    label = classify(net,im);                                              % Classify the picture
    title(char(label));                                                    % Show the class label
    drawnow
end