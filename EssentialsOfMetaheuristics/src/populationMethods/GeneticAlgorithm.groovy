package populationMethods

import java.util.Random
import singleStateMethods.Crossovers

class GeneticAlgorithm {
	// Algorithm 20
	
	// Our Algorithm takes a Genetic Algorithm Problem, a desired population size, and the number of runs to run
	def geneticAlgorithm(problem, populationSize, selectionMethod) {
		def popsize = populationSize
		def crossover = new Crossover()
		def selector = selectionMethod
		
		// Population
		def startingPopulation = []
		
		for(i in 0..popsize){
			def toAdd = problem.random()
			startingPopulation.add(toAdd) // Add a new random individual
		}
		
		def best = problem.create()
		def qualityOfBest = problem.quality(best)
		while(!problem.terminate(best, qualityOfBest)) {
			for(def individual: startingPopulation) {
				def newQuality = problem.quality(individual)
				if(newQuality > qualityOfBest) {
					best = individual
					qualityOfBest = newQuality
				}
				
			}
			
			def endingPopulation = []
			
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
