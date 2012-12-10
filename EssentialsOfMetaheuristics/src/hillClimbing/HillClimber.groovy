package hillClimbing

import problems.OnesMax

class HillClimber {	
	def problem
	
	// Happily this ended up being an almost direct copy from Sean's book.
	def maximize() {
		def s = problem.create()
		while (!problem.terminate(s)) {
			def r = problem.tweak(problem.copy(s))
			if (problem.quality(r) > problem.quality(s)) {
				s = r
			}
		}
		return s
	}	
}
