#加权图最短路径问题(无负权边)
'''
graph = {}
graph['start'] = {}
graph['start']['a'] = 6
graph['start']['b'] = 2
graph['a'] = {}
graph['a']['final'] = 1
graph['b'] = {}
graph['b']['final'] = 5
graph['b']['a'] = 3
graph['final'] = {}
infinity = float('inf')

costs = {}  #表示到某一点的开销
costs['a'] = 6
costs['b'] = 2
costs['final'] = infinity

parents = {}  #表示父节点
parents['a'] = 'start'
parents['b'] = 'start'
parents['final'] = None

processed = []  #记录已经处理过的节点


def find_lowest_search(costs):
    lowest_cost = infinity
    lowest_cost_node = None
    for i in costs.keys():
        if costs[i] < lowest_cost and i not in processed:
            lowest_cost = costs[i]
            lowest_cost_node = i
    return lowest_cost_node


def Dijkstra_Algorithm():
    node = find_lowest_search(costs)
    while node is not None:
        cost = costs[node]
        neighbours = graph[node]
        for i in neighbours.keys():
            new_cost = cost + neighbours[i]
            if costs[i] > new_cost:
                costs[i] = new_cost
                parents[i] = node
        processed.append(node)
        node = find_lowest_search(costs)


def main():
    Dijkstra_Algorithm()
    print(costs['final'])
    print(parents['final'])
    print(parents[parents['final']])


main()
'''

graph={}
graph['sheet']={}
graph['sheet']['CD']=5
graph['sheet']['paper']=0
graph['CD']={}
graph['CD']['drum']=20
graph['CD']['guitar']=15
graph['paper']={'guitar':30,'drum':35}
graph['drum']={'piano':10}
graph['guitar']={'piano':20}
graph['piano']={}
infinity=float('inf')
processed=[]

costs={'CD':5,'paper':0,'guitar':infinity,'drum':infinity,'piano':infinity}
parents={'CD':'sheet','paper':'sheet','guitar':None,'piano':None,'drum':None}
def find_lowest_node(costs):
    lowest_cost=infinity
    lowest_cost_node=None
    for i in costs.keys():
        if costs[i]<lowest_cost and i not in processed:
            lowest_cost=costs[i]
            lowest_cost_node=i
    return lowest_cost_node

def find_shortest_path():
    node=find_lowest_node(costs)
    while node is not None:
        neighbours=graph[node]
        for i in neighbours.keys():
            new_cost=costs[node]+neighbours[i]
            if new_cost<costs[i]:
                costs[i]=new_cost
                parents[i]=node
        processed.append(node)
        node=find_lowest_node(costs)

def main():
    find_shortest_path()
    print(costs['piano'])
    
main()


