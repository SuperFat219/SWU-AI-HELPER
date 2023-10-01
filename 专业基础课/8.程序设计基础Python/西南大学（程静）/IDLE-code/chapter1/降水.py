inches_str = input("How many inches of rain have fallen:")
inches_float = float(inches_str)
volume = (inches_float/12) * 43560
gallons = volume * 7.48051945
print("一英亩土地上的降雨量为",gallons,"加仑")
