package populationMethods
 
import problems.OnesMax
import groovy.transform.ToString

class ParentChildEvolution {
	Integer parents = 1
	Integer children = 1
	
	def maximize(problem){
		def childArr=[];
		for (int i=0; i<children;i++){
			childArr.add(problem.create()) //a random thing?
		}
		def best = null
		def bestQuality = null
		def childFitnessMap
		while(!problem.terminate(best, bestQuality)){ //while best is not ideal and we have time
			childFitnessMap = new TreeMap()
			for(stuff in childArr){		
				childFitnessMap.put(problem.quality(stuff), stuff)
			}
			def allkeys = childFitnessMap.keySet() //sorted in ascending order.
			def alphaKeys=[];
			for (def i = children-parents; i<children; i++){ //We need the best fitnesses: the last parent-number of keys.
				alphaKeys+= allkeys[i]
			}
			
			if (best == null ||  alphaKeys[parents-1]> bestQuality) {
				best = childFitnessMap.get(alphaKeys[parents-1])
				bestQuality = alphaKeys[parents-1]
			}
			childArr=[]
			def parent
			for(key in alphaKeys){
				parent=childFitnessMap.get(key)
				for(def i=0; i<children/parents; i++){
					childArr+= problem.tweak(parent)
				}
			}
		}
		return best
	}
	
	String toString(){
		"(" + parents+","+children+")Evo"
	}
} 