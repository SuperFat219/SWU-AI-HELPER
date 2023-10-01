function res = standard_res(X)
%求解n维数组的标准差
n=length(X);
res=sqrt((sum(X.^2)-n*mean(X)^2)/(n-1));
end

%     average_X=average(X);
%     sum1=0;
%     for i=1:length(X)
%         sum1=sum1+(X(i)-average_X)^2;
%     end;
%     sum1=sum1/length(X);
%     res=sqrt(sum1);