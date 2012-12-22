package singleStateMethods

import problems.OnesMax
import spock.lang.Specification

class HillClimberTest extends Specification {
	def "should work on small targets with plenty of evals"() {
		final NUM_BITS = 10
		def onesMax = new OnesMax(numBits : NUM_BITS, maxIterations : 1000)
		def hillClimber = new HillClimber()
		
		expect:
		hillClimber.maximize(onesMax) == [1]*NUM_BITS
	}
	
	def "won't work on large targets with few evals"() {
		final NUM_BITS = 1000
		def onesMax = new OnesMax(numBits : NUM_BITS, maxIterations : 100)
		def hillClimber = new HillClimber()
		
		expect:
		hillClimber.maximize(onesMax) != [1]*NUM_BITS

	}
}
