package populationMethods

import java.util.Random
import EssentialsOfMetaheuristics.singleStateMethods.Crossover

class TheGeneticAlgorithm {
	// Algorithm 20
	
	/** Our code makes several assumptions:
	 * The problem has a random method for creating new random individuals
	 * The problem has an isIdeal method for checking if our solution is ideal
	 * Individuals have a fitness method which will return their fitness
	 */
		
	
	// Our Algorithm takes a Genetic Algorithm Problem, a desired population size, and the number of runs to run
	def geneticAlgorithm(GAP, populationSize) {
		def popsize = populationSize //can change if you like
		def crossover = new Crossover()
		
		// Population
		def startingPopulation = [] as Set
		
		for(i in 0..popsize){
			def toAdd = GAP.random()
			startingPopulation.add(toAdd) // Add a new random individual
		}
		
		// Start best as just some random individual
		def best = GAP.random()
		while(!terminate(best, GAP.quality(best))) {
			for(def individual: startingPopulation) {
				GAP.quality(individual);
				if(individual.getFitness() > best.getFitness()) {
					best = individual
				}
				
			}
			
			def endingPopulation = [] as Set
			
			for(i in 0..(popsize/2)) {
				def parentA = selectWithReplacement(startingPopulation)
				def parentB = selectWithReplacement(startingPopulation)
				def children = crossover.twoPointCrossover(parentA, parentB)
				endingPopulation.add(GAP.tweak(children[0]), GAP.tweak(children[1]))
			}
			startingPopulation = endingPopulation
		}
		return best
	}
	
	def selectWithReplacement(population) {
		def rand = new Random()
		def randNumber = rand.nextInt(population.size())
		return population[randNumber]	
	}
}
