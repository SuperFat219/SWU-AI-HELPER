def parse_element(element_str):
    """Parse element string into symbol and quantity,
       e.g. Si2 returns ('Si',2)"""
    symbol_str=""
    quantity_str = ""
    for ch in element_str:
        if ch.isalpha():
            symbol_str = symbol_str + ch
        else:
            quantity_str = quantity_str + ch
    if quantity_str == "":  # if no number, default is 1
        quantity_str = "1"
    return symbol_str, int(quantity_str)
