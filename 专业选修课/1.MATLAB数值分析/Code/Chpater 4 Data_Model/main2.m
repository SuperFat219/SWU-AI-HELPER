clear;clc;
x=[0.1 0.2 0.15 0 -0.2 0.3];
y=[0.95 0.84 0.86 1.06 1.50 0.72];
p=polyfit(x,y,2);
figure;
xi=-0.2:0.01:0.3;
yi=polyval(p,xi);subplot(2,1,1);
plot(x,y,'o',xi,yi,'k');

p=polyfit(x,y,5);
yi=polyval(p,xi);subplot(2,1,2);
plot(x,y,'o',xi,yi,'k');

figure;
yy = nalagr(x,y,xi);
plot(x,y,'o',xi,yy,'k');
