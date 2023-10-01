% =========================================================================
% 名称：鸢尾花数据的K-means聚类方法
% 日期：2022.10.23
% 作者：冀杰
% 版本：MATLAB 2022a
% 本m文件调用MATLAB中鸢尾花的K-means聚类方法
% 该程序用于《人工智能基础》课程第二章仿真案例
% =========================================================================
rng(6,'twister')
load fisheriris
[cidx2,cmeans2] = kmeans(meas,2,'dist','sqeuclidean');
figure(1)
[silh2,h] = silhouette(meas,cidx2,'sqeuclidean');
figure(2)
ptsymb = {'bs','r^','md','go','c+'};
for i = 1:2
    clust = find(cidx2==i);
    plot3(meas(clust,1),meas(clust,2),meas(clust,3),ptsymb{i});
    hold on
end
plot3(cmeans2(:,1),cmeans2(:,2),cmeans2(:,3),'ko');
plot3(cmeans2(:,1),cmeans2(:,2),cmeans2(:,3),'kx');
hold off
xlabel('Sepal Length');
ylabel('Sepal Width');
zlabel('Petal Length');
view(-137,10);
grid on

[cidx3,cmeans3] = kmeans(meas,3,'Display','iter');
[cidx3,cmeans3,sumd3] = kmeans(meas,3,'replicates',5,'display','final');
sum(sumd3)
figure(3)
[silh3,h] = silhouette(meas,cidx3,'sqeuclidean');
figure(4)
for i = 1:3
    clust = find(cidx3==i);
    plot3(meas(clust,1),meas(clust,2),meas(clust,3),ptsymb{i});
    hold on
end
plot3(cmeans3(:,1),cmeans3(:,2),cmeans3(:,3),'ko');
plot3(cmeans3(:,1),cmeans3(:,2),cmeans3(:,3),'kx');
hold off
xlabel('Sepal Length');
ylabel('Sepal Width');
zlabel('Petal Length');
view(-137,10);
grid on
[mean(silh2) mean(silh3)]