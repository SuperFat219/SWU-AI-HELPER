    def eat(self):
        ''' Predator looks for one of the eight locations with Prey. If found,
        moves to that location, updates the starve clock, removes the Prey.
        '''
        location = self.check_grid(Prey)
        if location:
            self.island.remove(self.island.animal(location[0],location[1]))
            self.island.remove(self)
            self.x=location[0]
            self.y=location[1]
            self.island.register(self)
            self.starve_clock=self.starve_time


