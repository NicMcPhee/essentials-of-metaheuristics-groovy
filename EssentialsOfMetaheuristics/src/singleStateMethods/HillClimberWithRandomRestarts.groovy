package singleStateMethods

import java.util.Random

class HillClimberWithRandomRestarts {
    def Time = [1, 10, 15, 30, 100]
    Random rand = new Random()

    def maximize(problem) {
        def s = problem.create()
        def sQuality = problem.quality(s)
        def best = s
        def bestQuality = sQuality

        
        while (!problem.terminate(s, sQuality)) {
            def bestSample
            def bestSampleQuality = Integer.MIN_VALUE
            for(int i = 0; i < Time[rand.nextInt(Time.size())]; i++) {
                def r = problem.tweak(problem.copy(s))
                def rQuality = problem.quality(r)
                if (rQuality > bestSampleQuality) {
                    bestSample = r
                    bestSampleQuality = rQuality
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
        "HCWRR_" + Time
    }
}
