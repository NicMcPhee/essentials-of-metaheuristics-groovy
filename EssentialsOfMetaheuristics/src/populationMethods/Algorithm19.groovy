package populationMethods
import problems.OnesMax

class Algorithm19 {
	Integer numParents = 2
	Integer numChildren = 5
	def maximize(problem){
		def individualArr = []

		for (i in 0..numParents) {
			individualArr.add(problem.create())
		}
		

		def best = individualArr[0]

		while(!problem.terminate(best, problem.quality(best))) {
			for (individual in individualArr) {
				if (problem.quality(individual) > problem.quality(best)) {
					best = individual
				}
			}

			//Calculating the best of individualArr
			def sortedIndividualArr = []
			for (ind in individualArr){
				def indQuality = problem.quality(ind)
				def index = 0

				if(sortedIndividualArr.empty){
					sortedIndividualArr.add(0,ind)
				} else {
					while(index < sortedIndividualArr.size && indQuality < problem.quality(sortedIndividualArr.get(index))){
						index++
					}
					//Left off here, add to the arr at the right index
					sortedIndividualArr.add(index,ind)
				}

			}
			individualArr.clear()
			for (i in 0..numParents){
				individualArr.add(sortedIndividualArr.get(i))
			}
			
			for (i in 0..individualArr.size) {
				for (j in 0..(numChildren / numParents)) {
					individualArr.add(problem.tweak(problem.copy(individualArr.get(i))))
					}
			}
			sortedIndividualArr.clear()
			
			

		}
		return best
	}

	String toString() {
		"Algorithm19_" + numParents + "_" + numChildren
	}
}
