file_str = input("Open what file:")
find_line_str = input("Which line (integer):")

try:
    input_file = open(file_str)  # potential user error
    find_line_int = int(find_line_str)  # potential user error
    
except IOError:
    print("The file", file_str, "doesn't exist.")
except ValueError:
    print("Line", find_line_str, "isn't a legal line number.")

print("End of the program")
