% x = [0 10 20 30 40 50 60 70 80 90 100];
% t = [185 187 190 195 205 214 219 223 226 228 230];
x = [0 0.1 0.2 0.25 0.4 0.5];
t = [0 50 100 150 175 180];
x1 = [0 0.5 1 2 3 4 4.2 4.3 4.5 4.8 4.9 5];
t1 = [0 160 225 168 177 174 174 174 174 174 174 174];
p = polyfit(x1,t1,5); 
xi = 0:0.01:5;
yi = polyval(p,xi);

figure;

plot(xi,yi,LineWidth=2,LineStyle="-.");
hold on;
% plot([0,5],[180,180],LineWidth=2);
% scatter(x,t,'black','*'),legend('阶跃响应曲线','实际实验数据');
xlabel('时间/s');
ylabel('温度/℃')
hold on;

p2 = polyfit(x,t,2); 
xi = 0:0.01:0.45;
yi = polyval(p2,xi);
plot(xi,yi,LineWidth=2,LineStyle="--",Color='black');
ylim([0 250])


hold on;

x3 = [0 0.1 0.2 0.25 0.4 0.6 0.8];
t3 = [0 50 80 130 170 180.5 180];
p3 = polyfit(x3,t3,2); 
xi = 0:0.01:0.78;
yi = polyval(p3,xi);
plot(xi,yi,LineWidth=2,LineStyle=":",Color='black');
ylim([0 250])
plot([0.78,5],[180,180],LineWidth=2,LineStyle=":",Color='black'),legend('原始温度响应曲线','模糊PID控制温度响应曲线','PID控制温度响应曲线')
plot([0.45,5],[180,180],LineWidth=2,LineStyle="--",Color='black')