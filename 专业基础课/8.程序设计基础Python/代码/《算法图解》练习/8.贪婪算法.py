#集合覆盖问题
states_needed = set(['mt', 'wa', 'or', 'id', 'nv', 'ut','ca','az'])
stations = {
    'kone': set(['id', 'nv', 'ut']),
    'ktwo': set(['wa', 'id', 'mt']),
    'k3': set(['or', 'nv', 'ca']),
    'k4': set(['nv', 'ut']),
    'k5': set(['ca', 'az'])
}
final_stations = set()

while states_needed:
    states_covered = set()
    for station, states_for_station in stations.items():
        covered = states_needed & states_for_station
        if len(covered) > len(states_covered):
            best_station = station
            states_covered = covered
    final_stations.add(best_station)
    states_needed -= states_covered

print(final_stations)

#NP完全问题  旅行商问题
