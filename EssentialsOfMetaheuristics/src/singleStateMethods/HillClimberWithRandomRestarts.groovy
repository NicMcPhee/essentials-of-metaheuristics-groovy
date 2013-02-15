package singleStateMethods

import java.util.Random

class HillClimberWithRandomRestarts {
    // List of numbers representing the number of times the sample solution will be 
    // tweaked before the best sample is compared to the best solution. A random number
    // will be selected from this list.
    def restartTimeDistro = [10, 100, 1000]
    Random rand = new Random()

    def maximize(problem) {
        def s = problem.create()
        def sQuality = problem.quality(s)
        def best = s
        def bestQuality = sQuality
        while (!problem.terminate(s, sQuality)) {
            def bestSample
            def bestSampleQuality = Integer.MIN_VALUE
            def loops = restartTimeDistro[rand.nextInt(restartTimeDistro.size())]
            for(int i = 0; i < loops; i++) {
                def r = problem.tweak(problem.copy(s))
                def rQuality = problem.quality(r)
                if (rQuality > bestSampleQuality) {
                    bestSample = r
                    bestSampleQuality = rQuality
                }
                if(problem.evalCount >= problem.maxIterations) {
                    break;
                }
            }
            s = bestSample
            sQuality = bestSampleQuality
            if (sQuality > bestQuality) {
                best = s
                bestQuality = sQuality
            }
        }
        return best
    }

    String toString() {
        def distroNoSpaces = ""
        for(int i = 0; i < restartTimeDistro.size(); i++) {
            distroNoSpaces += restartTimeDistro[i] + "_"
        }
        distroNoSpaces = distroNoSpaces[0..distroNoSpaces.length()-2]
        "HCWRR_" + distroNoSpaces
    }
}
