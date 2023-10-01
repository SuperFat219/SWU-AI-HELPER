clear;clc;
odefun = @(t,y) y-2*t/y;
[t,y] = ode45(odefun,[0,4],1);
[t,y]
plot(t,y,'-o');
hold on;
x=0:0.001:4;
y=sqrt(1+2.*x);
plot(x,y,'Linewidth',2);
legend('数值解','准确解')