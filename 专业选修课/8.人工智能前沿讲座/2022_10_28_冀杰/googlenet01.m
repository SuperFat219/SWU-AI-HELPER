% =========================================================================
% 名称：利用googlenet进行图像分类并现实概率
% 日期：2022.10.23
% 作者：冀杰
% 版本：MATLAB 2022a
% 本m文件调用googlenet深度学习网络，并对某一幅图片进行分类，观察结果
% 该程序用于《人工智能基础》课程第二章仿真案例
% =========================================================================
clc;                                                                       % 清除命令行窗口
clear all;                                                                 % 清除所有数据
net = googlenet;                                                           % 调用googlenet网络
inputSize = net.Layers(1).InputSize                                        % 确认输入图像的像素
classNames = net.Layers(end).ClassNames;                                   % 显示网络输出的物体种类
numClasses = numel(classNames)                                             % 显示输出类型数量
disp(classNames(randperm(numClasses,10)))                                  % 随机显示十种类型
I = imread('peppers.png');                                                 % 读取红椒的图片信息
figure                                                                     % 打开图片
imshow(I)                                                                  % 显示图片
size(I)                                                                    % 显示尺寸
I = imresize(I,inputSize(1:2));                                            % 重新定义尺寸
figure                                                                     % 打开图片
imshow(I)                                                                  % 显示图片
[label,scores] = classify(net,I);                                          % 图像分类
label                                                                      % 现实标签
figure                                                                     % 打开图片
imshow(I)                                                                  % 显示图片
title(string(label) + ", " + num2str(100*scores(classNames == label),3) + "%"); % 标题
[~,idx] = sort(scores,'descend');                                          % 获取概率并降序排列
idx = idx(5:-1:1);                                                         % 选取前五名概率
classNamesTop = net.Layers(end).ClassNames(idx);                           % 前五名概率的名称
scoresTop = scores(idx);                                                   % 名称后面给出概率
figure                                                                     % 打开图片
barh(scoresTop)                                                            % 条形显示
xlim([0 1])                                                                % 横坐标
title('Top 5 Predictions')                                                 % 标题
xlabel('Probability')                                                      % x轴标题
yticklabels(classNamesTop)                                                 % y轴标题