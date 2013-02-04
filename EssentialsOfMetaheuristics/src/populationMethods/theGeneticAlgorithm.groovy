package populationMethods

class theGeneticAlgorithm {
	/** Our code makes several assumptions:
	 * The problem has a newRandomIndividual method for creating new random individuals
	 * The problem has an isIdeal method for checking if our solution is ideal
	 * Individuals have a method assessFitness which will their fitness
	 * Individuals have a fitness method which will return their fitness
	 * Individuals have a copy method
	 * Either the Individuals or the problem gives us the methods: selectWithReplacement, crossover, and mutate
	 */
		
	
	// Our Algorithm takes a Genetic Algorithm Problem, a desired population size, and the number of runs to run
	def geneticAlgorithm(GA, populationSize, numRuns) {
		def popsize = populationSize //can change if you like
		
		// Population
		def startingPopulation = [] as Set
		
		for(i in 0..popsize){
			def toAdd = GA.newRandomIndividual
			startingPopulation.add(toAdd) // Add a new random individual
		}
		
		def best
		def count = 0
		while(GA.isIdeal(best) || (count == numRuns)) {
			for(def individual: startingPopulation) {
				individual.assessFitness();
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
}
