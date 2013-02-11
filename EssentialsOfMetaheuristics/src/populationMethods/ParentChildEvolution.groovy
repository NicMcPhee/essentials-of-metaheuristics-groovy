package populationMethods

import problems.OnesMax
import groovy.transform.ToString

class ParentChildEvolution {
	Integer numParents = 1
	Integer numChildren = 1

	def maximize(problem){
		def childArr = [] //will be an array of [value,fitness] pairs
		def value
		numChildren.times{
			value = problem.create()
			childArr.add(new FitnessValuePair(value, problem.quality(value)))
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
					value = problem.tweak(parent)
					childArr.add(new FitnessValuePair(value, problem.quality(value)))
				}
			}
		}
		return best
	}

		String toString(){
		"(" + numParents+","+numChildren+")Evo"
	}
}