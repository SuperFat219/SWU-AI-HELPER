%ÊµÑé7 ×÷Í¼
%Time:2021.11.9

figure % new figure

subplot(2,2,1); 
%(1)y=x^2sin(x^2-x-2),-2<=x<=2
% fplot(@(x) x^2*sin(x^2-x-2),[-2,2],'b','Linewidth',2);
% grid on;
x=-2:0.001:2;
y=x.^2.*sin(x.^2-x-2);
plot(x,y);
title('y=x^2sin(x^2-x-2)');
ylabel('y');
xlabel('x');
grid on;

subplot(2,2,2); 
%(2)x^2/4+y^2/9=1
% ezplot('x^2/4+y^2/9=1');
% axis([-3 3 -5 5]);
% grid on;
xt=@(t) 2*cos(t);
yt=@(t) 3*sin(t);
fplot(xt,yt);
title('x^2/4+y^2/9=1')
ylabel('y');
xlabel('x');
ylim([-4 4]);
xlim([-3 3]);
grid on;

subplot(2,2,3);
[X,Y] = meshgrid(-3:3);
Z=X.^2+Y.^2;
mesh(Z);
title('Z=X^2+Y^2')
ylabel('y');
xlabel('x');
zlabel('z');


subplot(2,2,4);
[X,Y] = meshgrid(-13:13);
Z=X.^4+3*X.^2+Y.^2-2*X-2*Y-2*(X.^2.*Y)+6;
mesh(Z);
ylim([-3 3]);
xlim([-3 13]);
title('Z=X^4+3X^2+Y^2-2X-2Y-2X^2Y+6')
ylabel('y');
xlabel('x');
zlabel('z');

figure
xt = @(t) sin(t);
yt = @(t) cos(t);
zt = @(t) cos(2*t);
fplot3(xt,yt,zt,[0,2*pi]);

figure
% subplot(3,1,2);
t=linspace(0,2*pi);
p=linspace(0,pi/2);
[theta,phi]=meshgrid(t,p);                  
x=2*sin(phi).*cos(theta); 
y=2*sin(phi).*sin(theta);
z=2*cos(phi);
% colormap([1 0 0]);
mesh(x,y,z);
daspect([1,1,1]);

figure
x=0:0.0001:pi;
y1=sin(x);
y2=sin(x).*sin(10*x);
y3=-sin(x);
plot(x,y1,x,y2,x,y3,'Linewidth',1.5);
xlim([-0.5 3.5]);
ylim([-1.5 1.5]);