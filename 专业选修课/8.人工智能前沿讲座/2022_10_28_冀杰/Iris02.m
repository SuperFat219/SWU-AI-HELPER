% =========================================================================
% 名称：鸢尾花数据的线性分类
% 日期：2022.10.23
% 作者：冀杰
% 版本：MATLAB 2022a
% 本m文件调用MATLAB中鸢尾花的相关数据并进行线性分分类
% 该程序用于《人工智能基础》课程第二章仿真案例
% =========================================================================
load fisheriris                                                            % 导入鸢尾花数据
PL = meas(:,3);                                                            % 选取特征三
PW = meas(:,4);                                                            % 选取特征四
h1 = gscatter(PL,PW,species,'krb','ov^',[],'off');                         % 按照分类绘制点图
h1(1).LineWidth = 2;                                                       % 不同类型线宽
h1(2).LineWidth = 2;                                                       % 不同类型线宽
h1(3).LineWidth = 2;                                                       % 不同类型线宽
legend('Setosa','Versicolor','Virginica','Location','best')                % 类型名称
hold on
X = [PL,PW];                                                               % 保留数据
MdlLinear = fitcdiscr(X,species);                                          % 设计线性分类器
MdlLinear.ClassNames([2 3])                                                % 检索第二类和第三类之间最优系数
K = MdlLinear.Coeffs(2,3).Const;                                           % 线性公式的常数
L = MdlLinear.Coeffs(2,3).Linear;                                          % 线性公式的系数
f = @(x1,x2) K + L(1)*x1 + L(2)*x2;                                        % 确定线性公式
h2 = fimplicit(f,[.9 7.1 0 2.5]);                                          % 绘制隐函数
h2.Color = 'r';                                                            % 直线颜色
h2.LineWidth = 2;                                                          % 直线宽度
h2.DisplayName = 'Boundary between Versicolor & Virginica';                % 名称
MdlLinear.ClassNames([1 2])                                                % 检索第1类和第2类之间最优系数
K = MdlLinear.Coeffs(1,2).Const;                                           % 线性公式的常数
L = MdlLinear.Coeffs(1,2).Linear;                                          % 线性公式的系数
f = @(x1,x2) K + L(1)*x1 + L(2)*x2;                                        % 确定线性公式
h3 = fimplicit(f,[.9 7.1 0 2.5]);                                          % 绘制隐函数
h3.Color = 'k';                                                            % 直线颜色
h3.LineWidth = 2;                                                          % 直线宽度
h3.DisplayName = 'Boundary between Versicolor & Setosa';                   % 名称
axis([.9 7.1 0 2.5])                                                       % 坐标系范围
xlabel('Petal Length')                                                     % X轴标题
ylabel('Petal Width')                                                      % Y轴标题
title('{\bf Linear Classification with Fisher Training Data}')             % 图片标题