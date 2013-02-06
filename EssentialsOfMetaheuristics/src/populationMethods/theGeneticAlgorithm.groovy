package populationMethods

import java.util.Random
import singleStateMethods.Crossovers

class TheGeneticAlgorithm {
	// Algorithm 20
	
	/** Our code makes several assumptions:
	 * The problem has a random method for creating new random individuals
	 */
		
	
	// Our Algorithm takes a Genetic Algorithm Problem, a desired population size, and the number of runs to run
	def geneticAlgorithm(problem, populationSize, selectionMethod) {
		def popsize = populationSize //can change if need be
		def crossover = new Crossover()
		def selector = selectionMethod
		
		// Population
		def startingPopulation = [] as Set
		
		for(i in 0..popsize){
			def toAdd = problem.random()
			startingPopulation.add(toAdd) // Add a new random individual
		}
		
		// Start best as just some random individual
		def best = problem.random()
		while(!terminate(best, problem.quality(best))) {
			problem.population = startingPopulation
			for(def individual: startingPopulation) {
				problem.quality(individual);
				if(individual.getFitness() > best.getFitness()) {
					best = individual
				}
				
			}
			
			def endingPopulation = [] as Set
			
			for(i in 0..(popsize/2)) {
				def parentA = selector.maximize(problem, startingPopulation)
				def parentB = selector.maximize(problem, startingPopulation)
				def children = crossover.twoPointCrossover(parentA, parentB)
				endingPopulation.add(problem.tweak(children[0]), problem.tweak(children[1]))
			}
			startingPopulation = endingPopulation
		}
		return best
	}

}
