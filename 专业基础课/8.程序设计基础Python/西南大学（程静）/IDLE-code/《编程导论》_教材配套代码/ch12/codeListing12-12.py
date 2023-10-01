# multi-class model for the standard model, showing
# inheritance along __str__ use.

class Particle (object):
    def __init__ (self, name='', position=(0.0,0.0,0.0),\
                  velocity=(0.0,0.0,0.0), spin=0.0):
        self.position = position
        self.velocity = velocity
        self.name = name
        self.spin = spin

    def __str__ (self):
        # print 'in Particle str'
        pos_str = '({:.2}:{:.2}:{:.2})'.\
              format(self.position[0],self.position[1],self.position[2])
        vel_str = '({:.2}:{:.2}:{:.2})'.\
              format(self.velocity[0],self.velocity[1],self.velocity[2])
        result_str = "{}\n at {}\n with velocity {}\n and spin {}\n".\
                 format(self.name, pos_str, vel_str, self.spin)
        return result_str

class MassParticle (Particle):
    def __init__ (self, name='',position=(0.0,0.0,0.0),\
                  velocity=(0.0,0.0,0.0), spin=0.0, mass=0.0):
        Particle.__init__(self, name, position, velocity, spin)
        self.mass = mass

    def __str__(self):
        # print 'in mass str'
        result_str = Particle.__str__(self)
        result_str = result_str + ' and mass {}\n'.format(self.mass)
        return result_str

class ChargedParticle(MassParticle):
    def __init__(self, name='', position=(0.0,0.0,0.0),\
                 velocity=(0.0,0.0,0.0), spin=0.0,mass=0.0, charge=0.0):
        MassParticle.__init__(self,name,position,velocity,spin,mass)
        self.charge = charge

    def __str__(self):
        # print 'in charged str'
        result_str = MassParticle.__str__(self)
        result_str = result_str + ' and charge {}'.format(self.charge)
        return result_str
