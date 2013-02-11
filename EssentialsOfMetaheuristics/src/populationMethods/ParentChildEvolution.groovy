package populationMethods

import problems.OnesMax
import groovy.transform.ToString

class ParentChildEvolution {
	Integer numParents = 1
	Integer numChildren = 1

	def maximize(problem){
		assert numParents > 0 && numChilren >0, "numParents and numChildren must be positive"
		assert numChildren%numParents == 0, "numChildren must be a multiple of numParents" 
		def childArr = [] //will be an array of [fitness,value] pairs
		numChildren.times{
			def candidateSolution = problem.create()
			childArr.add(new FitnessValuePair(candidateSolution, problem.quality(candidateSolution)))
		}
		def best = childArr[0].getVal()
		def bestQuality = childArr[0].getFit()
		
		while(!problem.terminate(best, bestQuality)){ //while best is not ideal and we have time
			childArr.sort()
			if (childArr[0].getFit() > bestQuality) {
				best = childArr[0].getVal()
				bestQuality = childArr[0].getFit()
			}
			ArrayList parentList = new ArrayList()
			for(def i = 0; i<numParents; i++){
				parentList.add(childArr[i].getVal())
			}
			childArr= []
			for(parent in parentList){
				(numChildren/numParents).times{
					def nextSolution = problem.tweak(parent)
					childArr.add(new FitnessValuePair(nextSolution, problem.quality(nextSolution)))
				}
			}
		}
		return best
	}

		String toString(){
		"(" + numParents+","+numChildren+")Evo"
	}
}