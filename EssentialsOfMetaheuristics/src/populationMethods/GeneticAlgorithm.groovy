package populationMethods

import java.util.Random
import operators.Crossovers
import singleStateMethods.TournamentSelection

class GeneticAlgorithm {
	// Algorithm 20
	// crossover is assumed to be a closure
	def crossover = (new Crossovers()).onePointCrossover
	def selector = new TournamentSelection
	
	// Our Algorithm takes a Genetic Algorithm Problem, a desired population size, and the number of runs to run
	def geneticAlgorithm(problem, populationSize, selectionMethod=selector, crossoverMethod=crossover) {
		def popsize = populationSize
		selector=selectionMethod
		crossover = crossoverMethod
	
		// Population
		def startingPopulation = [] as Set
		
		popsize.times {
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
			
			def endingPopulation = [] as Set
			
			for(i in 0..(popsize/2)) {
				def parentA = selector.select(problem, startingPopulation)
				def parentB = selector.select(problem, startingPopulation)
				def children = crossover(parentA, parentB)
				endingPopulation.add(problem.tweak(children[0]), problem.tweak(children[1]))
			}
			startingPopulation = endingPopulation
		}
		return best
	}

}
