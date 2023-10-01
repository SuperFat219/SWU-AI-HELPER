#广度优先搜索
#非加权图最短路径问题

from collections import deque
graph = {}
graph['you'] = ['alice', 'bob', 'claire']
graph['bob'] = ['anuj', 'peggy']
graph['alice'] = ['peggy']
graph['claire'] = ['thom', 'jonny']
graph['anuj'] = []
graph['peggy'] = []
graph['thom'] = []
graph['jonny'] = []


def judegement(people):
    if people[-1] == 'm':
        return True
    return False


def search_name(name):
    search_queue = deque()
    search_queue += graph[name]
    #print(search_queue)
    searched = []
    while search_queue:
        person = search_queue.popleft()
        if person not in searched:
            if judegement(person):
                print(person + ' is a mango seller')
                return True
            else:
                search_queue += graph[person]
                searched.append(person)
    print('没有人')
    return False


search_name('you')
