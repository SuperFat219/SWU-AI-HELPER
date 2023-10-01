# Functions adapted from ProgrammingHistorian (updated to Python3)
# http://niche.uwo.ca/programming-historian/index.php/Tag_clouds

# Take one long string of words and put them in an HA\,\,BTA\,\,BMA\,\,BL box.
# If desired, width, background color & border can be changed in the function
# ThisA\kern-.2ptB functionA\kern-.2ptB stuffsA\kern-.2ptB theA\kern-.2ptB "body"A\kern-.2ptB stringA\kern-.2ptB intoA\kern-.2ptB theA\kern-.2ptB the A\kern-.5ptBHA\,\,BTA\,\,BMA\,\,BLA\kern-.5ptB formattingA\kern-.2ptB stringA\kern-.5ptB.
def make_HTML_box(body):
    box_str = """<div style=\"
    width: 560px;
    background-color: rgb(250,250,250);
    border: 1px grey solid;
    text-align: center\" >{:s}</div>
    """
    return box_str.format(body)

# TakeA\kern-.2ptB word(str)A\kern-.2ptB andA\kern-.2ptB fontsize(int),A\kern-.2ptB andA\kern-.2ptB createA\kern-.2ptB an A\kern-.5ptBHA\,\,BTA\,\,BMA\,\,BLA\kern-.5ptB wordA\kern-.2ptB inA\kern-.2ptB thatA\kern-.2ptB fontsizeA\kern-.5ptB.
# TheseA\kern-.2ptB wordsA\kern-.2ptB canA\kern-.2ptB beA\kern-.2ptB strungA\kern-.2ptB togetherA\kern-.2ptB andA\kern-.2ptB sentA\kern-.2ptB toA\kern-.2ptB theA\kern-.2ptB make A\kern-.5ptBHA\,\,BTA\,\,BMA\,\,BLbox()A\kern-.5ptB functionA\kern-.5ptB.
# ThisA\kern-.2ptB functionA\kern-.2ptB stuffsA\kern-.2ptB theA\kern-.2ptB bodyA\kern-.2ptB andA\kern-.2ptB fontsizeA\kern-.2ptB intoA\kern-.2ptB an A\kern-.5ptBHA\,\,BTA\,\,BMA\,\,BLA\kern-.5ptB wordA\kern-.2ptB formatA\kern-.2ptB stringA\kern-.5ptB.
def make_HTML_word(body, fontsize):
    word_str = '<span style=\"font-size:{:s}px;\">{:s}</span>'
    return word_str.format(str(fontsize), body)
