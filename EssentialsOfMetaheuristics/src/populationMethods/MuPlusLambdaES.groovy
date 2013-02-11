package populationMethods
import problems.OnesMax

class MuPlusLambdaES {
	Integer numParents = 2
	Integer numChildren = 5
	def maximize(problem){
		def individualArr = []

		numChildren.times {
			individualArr.add(problem.create())
		}


		def best = individualArr[0]
		def bestQuality = problem.quality(best)

		while(!problem.terminate(best, bestQuality)) {
			for (individual in individualArr) {
				if (problem.quality(individual) > bestQuality) {
					best = individual
					bestQuality = problem.quality(best)
				}
			}

			//Calculating the top numParent best of individualArr
			
			println("before sort")
			println(individualArr.sort{problem.quality(it)}.reverse())
			println("after sort")
			
//			for (i in 0..(numParents - 1)){
//				individualArr.add(sortedIndividualArr.get(i))
//			}

			//find out how to cut array after the numParent-th element
			
			for (i in 0..individualArr.size()-1) {
				for (j in 1..(numChildren / numParents)) {
					individualArr.add(problem.tweak(problem.copy(individualArr.get(i))))
				}
			}

			sortedIndividualArr.clear()

		}
		return best
	}

	String toString() {
		"MuPlusLambdaES_" + numParents + "_" + numChildren
	}
}
