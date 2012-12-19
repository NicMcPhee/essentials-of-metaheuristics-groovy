package singleStateMethods

import problems.OnesMax

class HillClimber {	
	def problem
	
	// Happily this ended up being an almost direct copy from Sean's book.
	def maximize() {
		def s = problem.create()
		def sQuality = problem.quality(s)
		while (!problem.terminate(s)) {
			def r = problem.tweak(problem.copy(s))
			def rQuality = problem.quality(r)
			if (rQuality > sQuality) {
				s = r
				sQuality = rQuality
			}
		}
		return s
	}	
}
