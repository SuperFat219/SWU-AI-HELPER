function res = average(X)
%  求n维数组的均值
    res=0;
    for i=1:length(X)
        res=res+X(i);
    end
    res=res/length(X);
end

