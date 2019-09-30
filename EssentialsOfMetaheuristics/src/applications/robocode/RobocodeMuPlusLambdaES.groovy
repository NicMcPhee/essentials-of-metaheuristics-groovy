package applications.robocode
import problems.OnesMax

/*
 * A modified mu+lambda that passes along all the K "best so far"
 * individuals to the quality function. This allows us to include
 * them in tournaments going forward both to increase the quality
 * of the competition and to reduce the tendency to just repeat
 * ourselves.
 */
class RoboCodeMuPlusLambdaES {
    Integer numParents = 2
    Integer numChildren = 6
    
    def maximize(problem){
        assert numParents > 0 && numChildren > 0, "Number of parents and children must be positive"
        assert numChildren % numParents == 0, "Number of children must be a multiple of the number of parents"
        
        def individualArr = []
        def bestSoFars = []

        numChildren.times {
            individualArr.add(problem.random())
        }

        def best = individualArr[0]
        def bestQuality = problem.quality(best, bestSoFars)

        while(!problem.terminate(best, bestQuality)) {
            for (individual in individualArr) {
                if (problem.quality(individual, bestSoFars) >= bestQuality) {
                    best = individual
                    bestQuality = problem.quality(best, bestSoFars)
                    println "Best fitness of ${bestQuality} is ${best}."
                    bestSoFars.push(best)
                    println "Best so fars = ${bestSoFars}"
                }
            }

            individualArr = individualArr.sort{problem.quality(it, bestSoFars)}.reverse()[0..<numParents]
            individualArr.each { print "(${it.id}, ${problem.quality(it, bestSoFars)}) " }
            println ""

            // def children = []
            for (i in 0..<numParents) {
                for (j in 0..<(numChildren / numParents)) {
                    individualArr.add(problem.tweak(problem.copy(individualArr.get(i))))
//                    children.add(problem.tweak(problem.copy(individualArr.get(i))))
                }
            }
            // individualArr = children

        }
        return best
    }

    String toString() {
        "MuPlusLambdaES_" + numParents + "_" + numChildren
    }
}
