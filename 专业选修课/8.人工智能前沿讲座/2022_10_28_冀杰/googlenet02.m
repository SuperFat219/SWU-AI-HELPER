% =========================================================================
% 名称：利用googlenet进行摄像头图像分类
% 日期：2022.10.23
% 作者：冀杰
% 版本：MATLAB 2022a
% 本m文件调用googlenet深度学习网络，并对摄像头得到的图像进行分类，观察结果
% 该程序用于《人工智能基础》课程第二章仿真案例
% =========================================================================
clc;                                                                       % 清除命令行窗口
clear all;                                                                 % 清除所有数据
camera = webcam;                                                           % 调用摄像头图像
net = googlenet;                                                           % 调用googlenet网络
inputSize = net.Layers(1).InputSize(1:2)                                   % 确认输入图像的像素

h = figure;                                                                % 建立图像
h.Position(3) = 2*h.Position(3);                                           % 图像位置
ax1 = subplot(1,2,1);                                                      % 左侧图片
ax2 = subplot(1,2,2);                                                      % 右侧图片
ax2.PositionConstraint = 'innerposition';                                  % 位置限制

while ishandle(h)
    % Display and classify the image
    im = snapshot(camera);                                                 % 网络图片截图
    image(ax1,im)                                                          % 截图放到左侧
    im = imresize(im,inputSize);                                           % 重新定义图片大小
    [label,score] = classify(net,im);                                      % 图片类型及得分
    title(ax1,{char(label),num2str(max(score),2)});                        % 标题

    % Select the top five predictions
    [~,idx] = sort(score,'descend');                                       % 概率得分排序
    idx = idx(5:-1:1);                                                     % 取前五名排序
    scoreTop = score(idx);                                                 % 列出每一个得分情况
    classes = net.Layers(end).Classes;                                     % 给出分类
    classNamesTop = string(classes(idx));                                  % 分类的名称

    % Plot the histogram
    barh(ax2,scoreTop)                                                     % 用直方图显示
    title(ax2,'Top 5')                                                     % 显示前五名
    xlabel(ax2,'Probability')                                              % 横坐标
    xlim(ax2,[0 1])                                                        % 坐标范围为0-1
    yticklabels(ax2,classNamesTop)                                         % 纵坐标显示
    ax2.YAxisLocation = 'right';                                           % 纵坐标标题位置

    drawnow
end