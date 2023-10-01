
class NewClass (object):
    def __init__(self, attribute='default', name='Instance'):
        self.name = name               # public attribute
        self.__attribute = attribute   # a "private" attribute
    def __str__(self):
        return '{} has attribute {}'.format(self.name, self.__attribute)
