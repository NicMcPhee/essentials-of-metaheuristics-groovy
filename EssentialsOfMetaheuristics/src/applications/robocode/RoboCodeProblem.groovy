package applications.robocode

/*
 *   id : an id used in the generation of the name of the class.
 *   enemy_energy : the coefficient for the enemy's energy
 *   my_energy : the coefficient for our energy
 *   angle_diff : the coefficient for the different in angles between us and the point and then and the point
 *   distance : the coefficient for the distance between the point and the enemy
 */

class RoboCodeProblem {
    Integer individualCount = 0
    Integer evalCount = 0
    Random random = new Random()
    public static final STDEV = 10
    
    def random() {
        ++individualCount
        [ 'id' : individualCount, 
            'enemy_energy' : random.nextGaussian()*STDEV,
            'my_energy' : random.nextGaussian()*STDEV,
            'angle_diff' : random.nextGaussian()*STDEV,
            'distance' : random.nextGaussian()*STDEV ]
    }
    
    def copy(individual) {
        return individual.clone()
    }
    
    def tweak(individual) {
        return individual
    }
    
    def quality(individual) {
        ++evalCount
        return 0
    }
    
    def terminate(bestIndividual, bestQuality) {
        evalCount > 100
    }
}
