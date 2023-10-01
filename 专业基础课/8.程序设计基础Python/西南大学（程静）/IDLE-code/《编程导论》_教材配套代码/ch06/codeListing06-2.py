# Conversion program

def celsius_to_fahrenheit(celsius_float):
    """ Convert Celsius to Fahrenheit."""
    return celsius_float * 1.8 + 32

# main part of the program
print("Convert Celsius to Fahrenheit.")
celsius_float = float(input("Enter a Celsius temp: "))
# call the conversion function
fahrenheit_float = celsius_to_fahrenheit(celsius_float)
# print the returned value
print(celsius_float," converts to ",fahrenheit_float," Fahrenheit")
