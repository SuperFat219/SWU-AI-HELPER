function A = equalsumpartition(l, n)
%EQPARTITON partitions the set {1,2,...,l} into n l/n-subsets, such that
% the sums of the elements in any one of the 1/n-subset are equal. 
%-------------------------------------------------------------
% let l = n*m, divide the set {1,2,..., l} into n subsets
% A_1,A_2, ..., A_n, such that 
% 1. |A_1| = |A_2| = ... = |A_n| = m;
% 2. sum(A_1) = sum (A_2) = ...= sum(A_n).
% EQPARTITON(l, n) returns an n x m array, the rows of which can be
% used as A_1,A_2, ..., A_n.
%
% Example: if l = 15, n = 5, then
%
%              1   8  15
%              2  10  12
% EQPARTITON = 3   7  14
%              4   9  11
%              5   6  13
%---------------------------
% Author:     Xiaomin Bao
% Created:    June  12, 2007
% Updated:    April 28, 2013
%---------------------------
m = l/n;
a = (1:n)';
b = (n:-1:1)';
A = zeros(n,m);
for i = 1:m
    if mod(i,2) == 1
        A(:,i) = n*(i-1) + a;
    else
        A(:,i) = n*(i-1) + b;
    end
end
if (m > 1) && (mod(m,2) == 1)
    c = zeros(n,1);
    j = 1;
    for i = n:-2:1
        c(i) = j;
        if i > 1
            c(i-1) = ceil(n/2) + j;
        end
        j = j + 1;
    end
    d = circshift(c,[-1 0]);
    A(:,2) = n + c;
    A(:,3) = 2*n + d;
end
