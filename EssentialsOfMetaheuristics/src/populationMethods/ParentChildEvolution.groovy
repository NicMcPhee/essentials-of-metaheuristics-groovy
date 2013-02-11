package populationMethods

import problems.OnesMax
import groovy.transform.ToString

class ParentChildEvolution {
	Integer numParents = 1
	Integer numChildren = 1

	def maximize(problem){
		assert numParents > 0 && numChildren >0, "numParents and numChildren must be positive"
		assert numChildren%numParents == 0, "numChildren must be a multiple of numParents"
		def childArr = [] //will be an array of [fitness,value] pairs
		numChildren.times{
			def candidateSolution = problem.create()
			childArr.add(new FitnessValuePair(value:candidateSolution,fitness:problem.quality(candidateSolution)))
		}
		def best = childArr[0].value
		def bestQuality = childArr[0].fitness

		while(!problem.terminate(best, bestQuality)){ //while best is not ideal and we have time
			childArr.sort()
			if (childArr[0].fitness > bestQuality) {
				best = childArr[0].value
				bestQuality = childArr[0].fitness
			}
			def parentList = []
			numParents.times{i->
				parentList.add(childArr[i].value)
			}
			childArr= []
			for(parent in parentList){
				(numChildren/numParents).times{
					def nextSolution = problem.tweak(parent)
					childArr.add(new FitnessValuePair(value:nextSolution, fitness:problem.quality(nextSolution)))
				}
			}
		}
		return best
	}

	String toString(){
		"(" + numParents+","+numChildren+")Evo"
	}
}