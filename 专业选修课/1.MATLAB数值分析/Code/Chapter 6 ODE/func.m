function f = func(t,x)
f(1) = -x(1)^3-x(2);
f(2) = x(1)-x(2)^3;
f=f(:); %使其为列向量
end

