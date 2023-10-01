x = linspace(-5,5,100000);
y = f(x);
plot(x,y,'LineWidth',1.5);
xlim([-5,5]);
ylim([-0.5,2]);
grid on;
hold on;

xx1 = linspace(-5,5,6);
yy1 = f(xx1);
y2 = nalagr(xx1,yy1,x);
plot(x,y2,'LineWidth',1.5);
hold on;

xx2 = linspace(-5,5,11);
yy2 = f(xx2);
y3 = nalagr(xx2,yy2,x);
plot(x,y3,'LineWidth',1.5,'color','m');
hold off;