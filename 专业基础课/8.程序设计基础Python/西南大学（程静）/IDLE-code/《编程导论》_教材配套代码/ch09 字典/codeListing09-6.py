def read_table(a_file, a_dict):
    """Read Periodic Table file into a dict. with element symbol as key.
        periodic_file is a file object opened for reading """
    data_reader = csv.reader(a_file)

    for row in data_reader:
        # ignore header rows: elements begin with a number
        if row[0].isdigit():  
            symbol_str = row[1]
            a_dict[symbol_str] = row[:8]  # ignore end of row
