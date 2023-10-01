% =========================================================================
% 名称：鸢尾花数据的非线性分类
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

MdlQuadratic = fitcdiscr(X,species,'DiscrimType','quadratic');             % 设计非线性分类器
MdlQuadratic.ClassNames([2 3])                                             % 检索第二类和第三类之间最优系数
K = MdlQuadratic.Coeffs(2,3).Const;                                        % 非线性分类器参数
L = MdlQuadratic.Coeffs(2,3).Linear;                                       % 非线性分类器参数
Q = MdlQuadratic.Coeffs(2,3).Quadratic;                                    % 非线性分类器参数
f = @(x1,x2) K + L(1)*x1 + L(2)*x2 + Q(1,1)*x1.^2 + ...
    (Q(1,2)+Q(2,1))*x1.*x2 + Q(2,2)*x2.^2;
h2 = fimplicit(f,[.9 7.1 0 2.5]);
h2.Color = 'r';
h2.LineWidth = 2;
h2.DisplayName = 'Boundary between Versicolor & Virginica';
MdlQuadratic.ClassNames([1 2])                                              
K = MdlQuadratic.Coeffs(1,2).Const;
L = MdlQuadratic.Coeffs(1,2).Linear; 
Q = MdlQuadratic.Coeffs(1,2).Quadratic;
f = @(x1,x2) K + L(1)*x1 + L(2)*x2 + Q(1,1)*x1.^2 + ...
    (Q(1,2)+Q(2,1))*x1.*x2 + Q(2,2)*x2.^2;
h3 = fimplicit(f,[.9 7.1 0 1.02]);                                         % Plot the relevant portion of the curve.
h3.Color = 'k';
h3.LineWidth = 2;
h3.DisplayName = 'Boundary between Versicolor & Setosa';
axis([.9 7.1 0 2.5])
xlabel('Petal Length')
ylabel('Petal Width')
title('{\bf Quadratic Classification with Fisher Training Data}')
hold off