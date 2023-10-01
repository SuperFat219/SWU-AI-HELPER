from goto import with_goto
@with_goto
def range(start, stop):
    i = start
    result = []
    label.begin
    if i == stop:
        goto.end
        result.append(i)
        i+=1
        goto .begin
    label.end
    return result
