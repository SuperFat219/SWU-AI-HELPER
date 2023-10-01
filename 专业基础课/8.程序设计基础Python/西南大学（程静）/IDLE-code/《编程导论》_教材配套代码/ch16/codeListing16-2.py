def factorial(n):
    """Recursive factorial."""
    if n == 1:
        return 1                   # base case
    else:
        return n * factorial(n-1)   # recursive case
