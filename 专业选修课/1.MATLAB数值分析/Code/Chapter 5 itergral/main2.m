clear;clc;
fun1 = @(x,y) 1./((1+x+y).^2);
res1 = integral2(fun1,0,1,0,3)

fun2 = @(x,y) log(2+x.^3+y.*cos(x));
res2 = integral2(fun2,-1,1,@(x)-sqrt(1-x.^2),@(x)sqrt(1-x.^2))

fun3 =@(x,y,z) x.*sin(y)+z.^2.*cos(y);
ymin = @(x)-sqrt(1-x.^2);
ymax = @(x)sqrt(1-x.^2);
zmin = @(x,y)-sqrt(1-x.^2-y.^2);
zmax = @(x,y)sqrt(1-x.^2-y.^2);
res3 = integral3(fun3,0,1,ymin,ymax,zmin,zmax)

fun = @(x,y) 1+x+y.^2;
%(x-1)^2+y^2<=1
% ymin = -1;ymax = 1;
% xmin = @(y)-sqrt(1-y.^2)+1;
% xmax = @(y) sqrt(1-y.^2)+1;
xmin = 0;xmax = 2;
ymin = @(x) -sqrt(1-(x-1).^2);
ymax =@(x)sqrt(1-(x-1).^2);
res = integral2(fun,xmin,xmax,ymin,ymax)