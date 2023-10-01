import string

def letter_count(a_str):
    """Return the count of letters in a_str."""
    count = 0
    for char in a_str:
        if char.lower() in string.ascii_lowercase:
            count += 1
    return count
