    def move(self):
        """Move to an open, neighboring position."""
        # neighbor offsets
        offset = [(-1,1),(0,1),(1,1),(-1,0),(1,0),(-1,-1),(0,-1),(1,-1)] 
        for i in range(len(offset)):
            x = self.x + offset[i][0]  # neighboring coordinates
            y = self.y + offset[i][1]
            if self.island.animal(x,y) == 0: # neighboring spot is open
                self.island.remove(self)  # remove from current spot
                self.x = x  # new coordinates
                self.y = y
                self.island.register(self) # register new coordinates
                break  # finished with move
