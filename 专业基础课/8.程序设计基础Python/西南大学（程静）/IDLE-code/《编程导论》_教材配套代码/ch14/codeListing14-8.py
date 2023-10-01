dig_str = input("Input an integer: ")
try:
    if dig_str.isdigit():
        dig_int = int(dig_str)
    else:
        raise ValueError(dig_str)    # raise an exception here!
except ValueError:
    print("Conversion to int Error: ", dig_str)
