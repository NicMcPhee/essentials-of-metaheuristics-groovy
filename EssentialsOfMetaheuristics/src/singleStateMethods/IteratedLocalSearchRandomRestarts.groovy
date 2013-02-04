package singleStateMethods

import problems.OnesMax
import groovy.transform.ToString


class IteratedLocalSearchRandomRestarts {
	def rand=new Random()
	def intermediateEvalCount = 0
	def iterationsSinceHomeBaseChange = 0

	def timeDistribution = {
		return (10..100)
	}

	def setTime(intervals){
		intermediateEvalCount=0
		return intervals[rand.nextInt(timeDistribution().size())]
	}

	def intermediateTerminate(time){
		return (intermediateEvalCount >= time)
	}

	def maximize(problem) {
		def t = timeDistribution()
		def s = problem.create()
		def sQuality = problem.quality(s)
		def home = s
		def homeQuality = sQuality
		def best = s
		def bestQuality = sQuality
		while (!problem.terminate(s, sQuality)) {
			def time = setTime(t)
			while(!intermediateTerminate(time) && !problem.terminate(s, sQuality)){ //should this be based on s or best?
				++intermediateEvalCount
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
			home = newHomeBase(home, s, homeQuality, sQuality)
			s = problem.perturb(home)
		}
		return best
	}

	def newHomeBase(currentHome, newHome, currentQuality = quality(currentHome), newQuality = quality(newHome), timesBeforeChange = 4){
		if((newQuality > currentQuality) || (iterationsSinceHomeBaseChange >= timesBeforeChange)){ 
			iterationsSinceHomeBaseChange = 0
			return newHome
		} else {
			++iterationsSinceHomeBaseChange
			return currentHome

		}
	}

	String toString() {
		"ILS-RR"
	}
}
