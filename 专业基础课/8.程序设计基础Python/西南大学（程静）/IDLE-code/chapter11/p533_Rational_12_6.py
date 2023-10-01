def lcm (a,b):
    """ Calculate the l owest common multiple of two positive integers. """
    print('in lcm')
    return (a*b)//gcd(a,b) 


def gcd(bigger, smaller):
    """ Calculate the greatest common divisor of two positive integers. """
    print('in gcd')
    if not bigger > smaller:
        bigger, smaller = smaller, bigger
    while smaller != 0:
        remainder = bigger % smaller 
        # print ( ' calculation , big :{} , small :{} , rem :{} '.\
        # format ( bigger , smaller , remainder ) ) # debugging
        bigger, smaller = smaller, remainder
    return bigger


class Rational(object):
    """ Rational with numerator and denominator. Denominator parameter defaults to 1"""
    def __init__(self,numer,denom=1):
        print('in constructor')
        self.numer = numer
        self.denom = denom

    def __str__(self):
        """ String representation for printing """
        print('in str')
        return str(self.numer)+'/'+str(self.denom)

    def __repr__(self):
        """ Used in interpreter . Call __str__ for now """
        print('in repr')
        return self.__str__()

    def __add__(self, param_Rational):
        """ Add two Rationals """
        print('in add')
        the_lcm = lcm(self.denom, param_Rational.denom)
        # multiply each by the lcm , then add
        numerator_sum = (the_lcm * self.numer/self.denom) + \
                        (the_lcm * param_Rational.numer/param_Rational.denom)
        return Rational(int(numerator_sum), the_lcm)

    def __sub__(self, param_Rational):
        """ Subtract two Rationals """
        print('in sub')
        # subtraction is the same but with '−' instead of '+ '
        the_lcm = lcm(self.denom, param_Rational.denom)
        numerator_diff = (the_lcm * self.numer/self.denom) - \
                         (the_lcm * param_Rational.numer/param_Rational.denom)
        return Rational(int(numerator_diff), the_lcm)


def main():
    one_half = Rational(1,2)
    two_fifths = Rational(2,5)
    sum_Rational = one_half + two_fifths
    print(sum_Rational)
    #sum_Rational
    #type(sum_Rational)
