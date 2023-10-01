term_1,term_2 = 0,1
the_sum = 1
i = 3
sign = 1
count = 0
while(abs(term_1-term_2)>1E-2):
    sign = (-1)*sign
    term_1 = term_2
    term_2 = 1/i
    the_sum = the_sum + sign*term_2
    i=i+2
    count =count+1
    print("term_1=",term_1,",term_2=",term_2,",i=",i,",count=",count)

print("pi=",4*the_sum)
