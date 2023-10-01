tic;
x=1:1:100;
% y=sin(x)
for i=1:100
    y(i)=sin(x(i))
end;
toc;
save();