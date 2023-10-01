% =========================================================================
% 名称：鸢尾花数据的线性分类的问题
% 日期：2022.10.23
% 作者：冀杰
% 版本：MATLAB 2022a
% 本m文件调用MATLAB中鸢尾花的相关数据并进行线性分类，观察存在的问题
% 该程序用于《人工智能基础》课程第二章仿真案例
% =========================================================================
load fisheriris
f = figure;
gscatter(meas(:,1), meas(:,2), species,'rgb','osd');
xlabel('Sepal length');
ylabel('Sepal width');
N = size(meas,1);
lda = fitcdiscr(meas(:,1:2),species);
ldaClass = resubPredict(lda);
ldaResubErr = resubLoss(lda)
figure
ldaResubCM = confusionchart(species,ldaClass);
figure(f)
bad = ~strcmp(ldaClass,species);
hold on;
plot(meas(bad,1), meas(bad,2), 'kx');
hold off;