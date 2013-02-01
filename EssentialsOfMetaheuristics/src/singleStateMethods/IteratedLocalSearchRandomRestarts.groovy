package singleStateMethods

import problems.OnesMax
import groovy.transform.ToString


class IteratedLocalSearchRandomRestarts {
	// Happily this ended up being an almost direct copy from Sean's book.
	def maximize(problem) {
		def t = problem.timeDistribution()
		def s = problem.create()
		def sQuality = problem.quality(s)
		def home = s
		def homeQuality = sQuality
		def best = s
		def bestQuality = sQuality
		while (!problem.terminate(s, sQuality)) {
			def time = problem.setTime(t)
			while(!problem.intermediateTerminate(time) || !problem.terminate(s, sQuality)){ //should this be based on s or best?
				def r = problem.tweak(problem.copy(s))
				def rQuality = problem.quality(r)
				if (rQuality > sQuality) {
					s = r
					sQuality = rQuality
				}
			}
			if(sQuality > bestQuality){
				best = s
				bestQuality=sQuality
			}
			home = problem.newHomeBase(home, s, homeQuality, sQuality)
			s = problem.perturb(home)
		}
		return best
	}

	String toString() {
		"ILS-RR"
	}
}
