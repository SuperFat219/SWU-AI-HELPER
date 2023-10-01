
builtin_dict = __builtins__.__dict__
print('Builtin dictionary has {} entries\n'.format(len(builtin_dict)))

for key,val in builtin_dict.items():
    print('key:: {:20s} val:: {}'.format(key,str(val)))
