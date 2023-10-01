clear;clc;
A=[4 1 -1;3 2 -6;1 -5 3];
B=[4 3 1;3 3 -5;1 -5 3];
C=[5 7 6 5;7 10 8 7;6 8 10 9;5 7 9 10];
N1=generate(5);
N2=generate(50);
N3=generate(500);
% det(N3)
% data=inv(N3);
% [vector,lambda]=eig(N3);
% dlmwrite('N3_inv.txt',data,' ');
% dlmwrite('N3_vector.txt',vector,' ');
% dlmwrite('N3_lambda.txt',lambda,' ');
% norm(N3,1)
% norm(N3,2)
% norm(N3,inf)
% norm(N3,'fro')
% cond(N3,1)
% cond(N3,2)
% cond(N3,inf)
norm(N3,inf)
norm(pinv(N3),inf)
norm(N3,inf)*norm(pinv(N3),inf)
norm(N3,inf)*norm(inv(N3),inf)
cond(N3,inf)
%N3