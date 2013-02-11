package singleStateMethods

import problems.OnesMax
import groovy.transform.ToString

class SteepestAscentHillClimberWithReplacement {	
	Integer numGradientSamples = 1
	
	def maximize(problem) {
		def s = problem.create()
		def sQuality = problem.quality(s)
		def best = s
		def bestQuality = sQuality
		
		while (!problem.terminate(s, sQuality)) {
			def bestSample
			def bestSampleQuality = Integer.MIN_VALUE
			numGradientSamples.times {
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
		"SAHCWR_" + numGradientSamples
	}
}
