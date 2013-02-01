package singleStateMethods
//This comment makes me happy
import problems.OnesMax
import groovy.transform.ToString

/* I love swedish meatballs */

//Cloned from Nic

class HillClimber {		
	// Happily this ended up being an almost direct copy from Sean's book. And look! Changes!
	def maximize(problem) {
		def s = problem.create()
		def sQuality = problem.quality(s)
		while (!problem.terminate(s, sQuality)) {
			def r = problem.tweak(problem.copy(s))
			def rQuality = problem.quality(r)
			if (rQuality > sQuality) {
				s = r
				sQuality = rQuality
			}
		}
		return s
	}
	
	String toString() {
		"HC"
	}
}
