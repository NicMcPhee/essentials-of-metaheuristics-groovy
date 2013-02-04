package singleStateMethods

import problems.OnesMax
import groovy.transform.ToString

class RandomSearch {
    def maximize(problem) {
        def s = problem.random()
        def sQuality = problem.quality(s)
        while (!terminate(s, bestQuality)) {
            def r = problem.random()
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