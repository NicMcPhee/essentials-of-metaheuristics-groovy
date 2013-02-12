package singleStateMethods

import groovy.transform.ToString

class RandomSearch {
    def maximize(problem) {
        def s = problem.random()
        def sQuality = problem.quality(s)
        while (!problem.terminate(s, sQuality)) {
            def r = problem.random()
            def rQuality = problem.quality(r)
            if (rQuality > sQuality) {
                s = r
                sQuality = rQuality
            }
        }
        return s
    }
    String toString() {
        "RS"
    }
}