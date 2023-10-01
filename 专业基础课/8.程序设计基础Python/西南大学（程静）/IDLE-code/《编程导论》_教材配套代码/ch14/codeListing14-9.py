import string

# define our own exceptions
class NameException (Exception): 
    ''' For malformed names'''
    pass
class PasswordException (Exception):
    ''' For bad password '''
    pass
class UserException (Exception):
    ''' Raised for existing or missing user '''
    pass

def check_pass(pass_str, target_str):
    """Return True, if password contains characters from target."""
    for char in pass_str:
        if char in target_str:
            return True
    return False

class PassManager(object):
    """A class to manage a dictionary of passwords with error checking."""
    def __init__(self, init_dict=None):
        if init_dict==None:
            self.pass_dict={}
        else:
            self.pass_dict = init_dict.copy()

    def dump_passwords(self):
        return self.pass_dict.copy()

    def add_user(self,user):
        """Add good user name and strong password to password dictionary."""
        if not isinstance(user,str) or not user.isalnum():
            raise NameException
        if user in self.pass_dict:
            raise UserException
        pass_str = input('New password:')
        # strong password must have digits, uppercase and punctuation
        if  not (check_pass(pass_str, string.digits) and\
                     check_pass(pass_str, string.ascii_uppercase) and\
                     check_pass(pass_str, string.punctuation)):
            raise PasswordException
            
    def validate(self,user):
        """Return True, if valid user and password."""
        if not isinstance(user,str) or not user.isalnum():
            raise NameException
        if user not in self.pass_dict:
            raise UserException
        password = input('Passwd:')
        return self.pass_dict[user]==password
