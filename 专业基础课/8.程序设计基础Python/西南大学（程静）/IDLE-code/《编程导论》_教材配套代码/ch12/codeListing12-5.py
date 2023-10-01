
def lcm (a,b):
    """Calculate the lowest common multiple of two positive integers."""
    return (a*b)//gcd(a,b)  # Equation 12.1, // ensures an int is returned
