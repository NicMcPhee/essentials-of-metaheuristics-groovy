package populationMethods

import problems.OnesMax
import groovy.transform.ToString
import java.util.ArrayList

class ParentChildEvolution {
	Integer parents = 1
	Integer children = 1

	def maximize(problem){
		ArrayList<FitnessValuePair> childArr = new ArrayList()
		def value
		for (int i=0; i<children;i++){
			value = problem.create()
			childArr.add(new FitnessValuePair(value, problem.quality(value)))
		}
		def best = null
		def bestQuality = -1
		
		while(!problem.terminate(best, bestQuality)){ //while best is not ideal and we have time
			childArr.sort()
			if (best == null ||  childArr.get(0).getFit() > bestQuality) {
				best = childArr.get(0).getVal()
				bestQuality = childArr.get(0).getFit()
			}
			ArrayList parentList = new ArrayList()
			for(def i = 0; i<parents; i++){
				parentList.add(childArr.get(i).getVal())
			}
			childArr.clear()
			for(def j=0; j<parents; j++){
				for(def i=0; i<children/parents; i++){
					value = problem.tweak(parentList[j])
					childArr.add(new FitnessValuePair(value, problem.quality(value)))
				}
			}
		}
		return best
	}

		String toString(){
		"(" + parents+","+children+")Evo"
	}
}