package singleStateMethods

import problems.OnesMax
import groovy.transform.ToString


class SteepestAscentHillClimber {
	Integer numGradientSamples = 1
	
	def maximize(problem) {
		def s = problem.create()
		def sQuality = problem.quality(s)
		
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
			if (bestSampleQuality > sQuality) {
				s = bestSample
				sQuality = bestSampleQuality
			}
		}
		return s
	}
	
	String toString() {
		"SAHC_" + numGradientSamples
	}
}
