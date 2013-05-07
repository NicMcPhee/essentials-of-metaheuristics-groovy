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
    Integer maxEvalCount = 100
    Random random = new Random()
    RobotBuilder robotBuilder = new RobotBuilder("templates/HawkOnFireOS.template")
    BattleRunner battleRunner = new BattleRunner("templates/battle.template")
    public static final STDEV = 10
    def fitnesses = [:]
    
    def labels = ['my_velocity', 'enemy_velocity', 'velocity_diff', 'my_heading',
        'enemy_heading', 'heading_diff', 'energy_ratio', 'energy_diff',
        'enemy_energy', 'my_energy', 'angle_diff', 'distance', 'center_dist', 'aim_tolerance']
    
    def random() {
        ++individualCount
        def individual = [ 'id' : individualCount ]
        labels.each { label ->
            individual[label] = random.nextGaussian()*STDEV
        }
        return individual
    }
    
    def copy(individual) {
        return individual.clone()
    }
    
    def tweak(individual) {
        ++individualCount
        def result = [ 'id' : individualCount ]
        labels.each { label ->
            result[label] = individual[label] + random.nextGaussian()
        }
        return result
    }
    
    def quality(individual, bestSoFars = []) {
        if (fitnesses[individual['id']]) {
            return fitnesses[individual['id']]
        }
        ++evalCount
        robotBuilder.buildJarFile(individual)
        battleRunner.buildBattleFile(individual['id'], bestSoFars)
        def score = -battleRunner.runBattle(individual['id'])
        fitnesses[individual['id']] = score
        return score
    }
    
    def terminate(bestIndividual, bestQuality) {
        evalCount > maxEvalCount
    }
}
