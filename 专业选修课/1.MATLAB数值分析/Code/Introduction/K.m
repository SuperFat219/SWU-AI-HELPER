%สตั้5
%Time:2021.11.9
clear all;
clc;
tic;
sum1=0;
for i=1:10e5
    sum1=sum1+sqrt(3)/(2^i);
end;
toc;
sum1
tic;
sum2=0;
j=10e5;
while j>=1
    sum2=sum2+sqrt(3)/(2^j);
    j=j-1;
end;
toc;
sum2

tic;
A=1:10e5;
x=sqrt(3)*(2.^-A);
sum(x)
toc;


