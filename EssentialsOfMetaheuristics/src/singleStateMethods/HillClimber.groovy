package singleStateMethods
//This comment makes me happy
import problems.OnesMax
import groovy.transform.ToString

/* I love swedish meatballs */

//Cloned from Nic

// CLONED FROM NIC!
//This is a clone of Nic's files
// STOLEN FROM NIC MCPHEE WHO STOLE IT FROM SEAN LUKE
class HillClimber {
	//  Happily this ended up being an almost direct copy from Sean's book.
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
	// clowns 
	String toString() {
		"HC"
	}
}
