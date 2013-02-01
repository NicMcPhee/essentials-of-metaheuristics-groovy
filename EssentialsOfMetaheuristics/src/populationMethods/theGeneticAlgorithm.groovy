package populationMethods

class theGeneticAlgorithm {
	// Our Algorithm takes a Genetic Algorithm, a desired population size, and the number of runs to run
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
			for(def person: startingPopulation) {
				assessFitness(person)
				if(best == null || fitness(person) > fitness(best)) {
					best = person
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
