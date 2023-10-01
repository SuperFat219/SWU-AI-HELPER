    def breed(self):
        ''' Breed a new Animal.  If there is room in one of the 8 locations,
        place the new Prey there. Otherwise, you have to wait.
        '''
        if self.breed_clock <= 0:
            location = self.check_grid(int)
            if location:
                self.breed_clock = self.breed_time
                the_class = self.__class__
                new_animal = the_class(self.island,x=location[0],y=location[1])
                self.island.register(new_animal)
