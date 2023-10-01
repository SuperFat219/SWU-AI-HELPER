def reduce_rational(self):
    """ Return the reduced fractional value as a Rational"""
    print('in reduce')
    # find the gcd and then divide numerator and denominator by gcd
    the_gcd = gcd(self.numer,self.denom)
    return Rational(self.numer//the_gcd, self.denom//the_gcd)

def __eq__(self,param_Rational):
    """ Compare two Rationals for equality, return Boolean"""
    print('in eq')
    # reduce both; then check that numerators and denominators are equal
    reduced_self = self.reduce_rational()
    reduced_param = param_Rational.reduce_rational()
    return reduced_self.numer == reduced_param.numer and\
        reduced_self.denom == reduced_param.denom
