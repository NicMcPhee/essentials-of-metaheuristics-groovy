package populationMethods

import problems.OnesMax
import groovy.transform.ToString
import java.util.ArrayList

class ParentChildEvolution {
	Integer numParents = 1
	Integer numChildren = 1

	def maximize(problem){
		def childArr = []
		def value
		for (int i=0; i<numChildren;i++){
			value = problem.create()
			childArr.add(new FitnessValuePair(value, problem.quality(value)))
		}
		def best = null
		def bestQuality = -1
		
		while(!problem.terminate(best, bestQuality)){ //while best is not ideal and we have time
			childArr.sort()
			if (best == null ||  childArr[0].getFit() > bestQuality) {
				best = childArr[0].getVal()
				bestQuality = childArr[0].getFit()
			}
			ArrayList parentList = new ArrayList()
			for(def i = 0; i<numParents; i++){
				parentList.add(childArr[i].getVal())
			}
			childArr= []
			for(def j=0; j<numParents; j++){
				for(def i=0; i<numChildren/numParents; i++){
					value = problem.tweak(parentList[j])
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