package populationMethods

import java.util.Random

class theGeneticAlgorithm {
	// Algorithm 20
	
	/** Our code makes several assumptions:
	 * The problem has a newRandomIndividual method for creating new random individuals
	 * The problem has an isIdeal method for checking if our solution is ideal
	 * The problem will have a method assessFitness
	 * Individuals have a fitness method which will return their fitness
	 * Individuals have a copy method
	 * The Individual gives us the methods: crossover, and mutate
	 */
		
	
	// Our Algorithm takes a Genetic Algorithm Problem, a desired population size, and the number of runs to run
	def geneticAlgorithm(GAP, populationSize, numRuns) {
		def popsize = populationSize //can change if you like
		
		// Population
		def startingPopulation = [] as Set
		
		for(i in 0..popsize){
			def toAdd = GAP.newRandomIndividual
			startingPopulation.add(toAdd) // Add a new random individual
		}
		
		def best
		def count = 0
		while(!GAP.isIdeal(best) && (count != numRuns)) {
			for(def individual: startingPopulation) {
				GAP.assessFitness(individual);
				if(best == null || individual.getFitness() > best.getFitness()) {
					best = individual
				}
				
			}
			
			def endingPopulation = [] as Set
			
			for(i in 0..(popsize/2)) {
				def parentA = selectWithReplacement(startingPopulation)
				def parentB = selectWithReplacement(startingPopulation)
				def childA = crossover(copy(parentA), copy(parentB))
				def childB = crossover(copy(parentA), copy(parentB))
				endingPopulation.add(mutate(childA), mutate(childB))
			}
			startingPopulation = endingPopulation
			count++
		}
		return best
	}
	
	def selectWithReplacement(population) {
		def rand = new Random()
		def randNumber = rand.nextInt(population.size())
		return population[randNumber]	
	}
}
