package singleStateMethods

import problems.OnesMax
import groovy.transform.ToString

class SteepestAscentHillClimberWithReplacement {
    Integer numGradientSamples = 10

    def maximize(problem) {
        def s = problem.create()
        def sQuality = problem.quality(s)
        def best = s
        def bestQuality = sQuality

        while (!problem.terminate(s, sQuality)) {
            def bestSample
            def bestSampleQuality = Integer.MIN_VALUE
            for(int i = 0; i < numGradientSamples; i++) {
                def r = problem.tweak(problem.copy(s))
                def rQuality = problem.quality(r)
                if (rQuality > bestSampleQuality) {
                    bestSample = r
                    bestSampleQuality = rQuality
                }
                if(problem.terminate(bestSample, bestSampleQuality)) {
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
        return s
    }

    String toString() {
        "SAHCWR_" + numGradientSamples
    }
}
