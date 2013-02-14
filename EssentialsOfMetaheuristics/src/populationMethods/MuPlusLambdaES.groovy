package populationMethods
import problems.OnesMax

class MuPlusLambdaES {
    Integer numParents = 2
    Integer numChildren = 6
    
    def maximize(problem){
        assert numParents > 0 && numChildren > 0, "Number of parents and children must be positive"
        assert numChildren % numParents == 0, "Number of children must be a multiple of the number of parents"
        
        def individualArr = []

        numChildren.times {
            individualArr.add(problem.random())
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

            individualArr = individualArr.sort{problem.quality(it)}.reverse()[0..<numParents]

            for (i in 0..<numParents) {
                for (j in 0..<(numChildren / numParents)) {
                    individualArr.add(problem.tweak(problem.copy(individualArr.get(i))))
                }
            }

        }
        return best
    }

    String toString() {
        "MuPlusLambdaES_" + numParents + "_" + numChildren
    }
}
