
def fibo_generator():
    first, second = 0,1
    while True:
        yield first
        first,second = second, first + second

fibo_generator_object = fibo_generator()

print(next(fibo_generator_object))
print(next(fibo_generator_object))

for count in range(100):
    print(next(fibo_generator_object), end=', ')

