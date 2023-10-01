def gcd(bigger, smaller):
2 """ Calculate the gre a te s t common divisor of two p os i t i v e i n t e g e r s . """
3 print('in gcd')
4 if not bigger > smaller: # swap i f necessary s o bigger > smaller
5 bigger, smaller = smaller, bigger
6 while smaller != 0: # 1. i f smaller == 0, halt
7 remainder = bigger % smaller # 2. find remainder
8 # print ( ' calculation , big :{} , small :{} , rem :{} '.\
9 # format ( bigger , smaller , remainder ) ) # debugging
10 bigger, smaller = smaller, remainder # 3. reapply
11 return bigger
12
13 def lcm (a,b):
14 """ Calculate the l owest common multiple of two p o s i t i v e i n t e g e r s . """
15 print('in lcm')
16 return (a*b)//gcd(a,b) # Equation 12.1, / / ensures an int i s returned
17
18
19 class Rational(object):
20 """ Rational with numerator and denominator . Denominator
21 parameter def aults to 1"""
22 def init (self,numer,denom=1):
23 print('in constructor')
24 self.numer = numer
25 self.denom = denom
26
27 def str (self):
28 """ String representation for printing """
29 print('in str')
30 return str(self.numer)+'/'+str(self.denom)
31
32 def repr (self):
33 """ Used in i n t e r p r e t e r . Call str f or now """
34 print('in repr')
35 return self. str ()
36
37
38 def add (self, param Rational):
39 """ Add two Rationals """
40 print('in add'
