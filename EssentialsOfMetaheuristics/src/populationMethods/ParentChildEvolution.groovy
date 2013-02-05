package populationMethods

import problems.OnesMax
import groovy.transform.ToString
import java.util.ArrayList

class ParentChildEvolution {
	Integer parents = 1
	Integer children = 1

	def maximize(problem){
		def child=[];
		ArrayList childArr = new ArrayList()
		for (int i=0; i<children;i++){
			child = (problem.create()) //a random thing?
			childArr.add(child)
		}
		System.out.println(childArr)
		def best = null
		def bestQuality = -1
		def childFitnessMap
		while(!problem.terminate(best, bestQuality)){ //while best is not ideal and we have time
			childFitnessMap = new TreeMap()
			for(problemChild in childArr){
				childFitnessMap.put(problem.quality(problemChild), problemChild)
			}
			System.out.println(childFitnessMap)
			def allKeys = childFitnessMap.keySet() as List  //sorted in ascending order.
			System.out.println(allKeys)

			if (best == null ||  allKeys[allKeys.size()-1]> bestQuality) {
				best = pullElement(childFitnessMap.getAt(allKeys[allKeys.size()-1]))
				bestQuality = allKeys[allKeys.size()-1]
			}
			childArr=[]
			def parentList = getParents(allKeys, childFitnessMap)
			for(def j=0; j<parents; j++){
				for(def i=0; i<children/parents; i++){
					childArr+= problem.tweak(parentList[j])
				}
			}
		}
		return best
	}

	def pullElement(arr, index = 0){
		if(arr.class.isArray()){
			arr[index]
		}
		else{arr}
	}

	def getParents(keys, map){
		def parentList = []
		def maxParents = 0
		def whichKey = 1
		while(maxParents < parents){
			def nextParent = map.getAt(keys[keys.size()-whichKey])
			if(nextParent.getClass()== ArrayList){
				for(def i=0; i<nextParent.size() && i<parents; i++){
					parentList += nextParent[i]
					maxParents++
				}
			}
			else{
				parentList += nextParent
				maxParents++
			}
			whichKey++
		}
		System.out.println(parentList)
		parentList
	}

	String toString(){
		"(" + parents+","+children+")Evo"
	}
}