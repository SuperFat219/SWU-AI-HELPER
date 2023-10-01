% =========================================================================
% 名称：鸢尾花数据导入与分析
% 日期：2022.10.23
% 作者：冀杰
% 版本：MATLAB 2022a
% 本m文件调用MATLAB中鸢尾花的相关数据并进行分析分析
% 该程序用于《人工智能基础》课程第二章仿真案例
% =========================================================================
load iris.dat                                                % 导入鸢尾花数据
iris(:,1:4)=iris(:,1:4)/10;                                                % 鸢尾花数据矫正
setosaIndex = iris(:,5)==1;                                                % 定义鸢尾花类型
versicolorIndex = iris(:,5)==2;                                            % 定义鸢尾花类型
virginicaIndex = iris(:,5)==3;                                             % 定义鸢尾花类型

setosa = iris(setosaIndex,:);                                              % 选择各类型鸢尾花的数据
versicolor = iris(versicolorIndex,:);                                      % 选择各类型鸢尾花的数据
virginica = iris(virginicaIndex,:);                                        % 选择各类型鸢尾花的数据
Characteristics = {'sepal length','sepal width',...
    'petal length','petal width'};                                         % 定义不同的参数
pairs = [1 2; 1 3; 1 4; 2 3; 2 4; 3 4];                                    % 不同的参数配对

for i = 1:6                                                                % 不同参数配对数量
    x = pairs(i,1);                                                        % 不同配对的横坐标
    y = pairs(i,2);                                                        % 不同配对的纵坐标
    subplot(2,3,i)                                                         % 子图片的编号
    plot([setosa(:,x) versicolor(:,x) virginica(:,x)],...
         [setosa(:,y) versicolor(:,y) virginica(:,y)], '.')                % 回执不同参数的结果
    xlabel(Characteristics{x})                                             % 横坐标标题
    ylabel(Characteristics{y})                                             % 纵坐标标题
end                                                                        % 循环结束