def gcd(bigger, smaller):
    """Calculate the greatest common divisor of two positive integers."""
    if not bigger > smaller:        # swap if necessary so bigger > smaller
        bigger, smaller = smaller, bigger
    while smaller != 0:                       # 1. if smaller == 0, halt
        remainder = bigger % smaller          # 2. find remainder
        print('calculation, big:{}, small:{}, rem:{}'.\
              format(bigger, smaller, remainder)) # debugging
        bigger, smaller = smaller, remainder  # 3. reapply
    return bigger
