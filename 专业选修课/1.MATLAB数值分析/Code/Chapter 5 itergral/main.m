clear;clc;
x = -1:0.1:1;
y=exp(-x.^2);
trapz(x,y)
