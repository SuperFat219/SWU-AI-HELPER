clear;clc;
t=0:24;
T=[15 14 14 14 14 15 16 18 20 22 23 25 28 31 32 31 29 27 25 24 22 20 18 17 16];

figure;
subplot(2,2,1);
plot(t,T);
grid on;
hold on;
fun = @(c1,x) c1(1).*x.^2+c1(2).*x+c1(3);
c1 = lsqcurvefit(fun,[0 0 0],t,T);
c1
n1=norm(fun(c1,t)-T)
plot(t,fun(c1,t))
legend('原始数据','二次函数拟合');

subplot(2,2,2);
plot(t,T);
hold on;
grid on;
fun = @(c2,x) c2(1).*x.^3+c2(2).*x.^2+c2(3)*x+c2(4);
c2 = lsqcurvefit(fun,[0 0 0 0],t,T)
c2;
n2=norm(fun(c2,t)-T)
plot(t,fun(c2,t));
legend('原始数据','三次函数拟合');

subplot(2,2,3);
plot(t,T);
hold on;
grid on;
fun = @(c3,x) c3(1).*x.^4+c3(2).*x.^3+c3(3)*x.^2+c3(4)*x+c3(5);
c3 = lsqcurvefit(fun,[0 0 0 0 0],t,T);
c3
n3=norm(fun(c3,t)-T)
plot(t,fun(c3,t));
legend('原始数据','四次函数拟合');

subplot(2,2,4);
plot(t,T);
hold on;
grid on;
fun = @(c4,t) c4(1)*exp(-c4(2)*(t-c4(3)).^2);
options = optimoptions('lsqcurvefit','Algorithm','levenberg-marquardt');
lb = [];
ub = [];
c4 = lsqcurvefit(fun,[28 0.005 14],t,T,lb,ub,options);
c4
tt = 0:0.01:24;
n4=norm(fun(c4,t)-T)
plot(tt,fun(c4,tt));
legend('原始数据','指数函数拟合');

figure(5)
plot(t,T);
hold on;
grid on;
p = polyfit(t,T,5);
T2 = polyval(p,t);
plot(t,T2);

